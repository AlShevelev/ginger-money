package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class AddPaymentModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    AddPaymentModel {

    override fun getCreateAt(): ZonedDateTime = ZonedDateTime.now()
}