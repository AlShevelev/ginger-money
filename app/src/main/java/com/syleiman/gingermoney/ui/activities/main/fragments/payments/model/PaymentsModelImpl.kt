package com.syleiman.gingermoney.ui.activities.main.fragments.payments.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsList
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import javax.inject.Inject

class PaymentsModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    PaymentsModel {

    override suspend fun getPaymentsList(): ModelCallResult<out PaymentsList> =
        getValue {
            val hasAccounts = db.readAccounts().isNotEmpty()

            if(!hasAccounts) {
                PaymentsList(hasAccounts, listOf())
            } else {
                PaymentsList(hasAccounts, listOf())
            }
        }
}