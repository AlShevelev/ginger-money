package com.syleiman.gingermoney.ui.activities.main.fragments.payments.model

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacade
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsList
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsListPeriodInfo
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class PaymentsModelImpl
@Inject
constructor(
    private val db: DbStorageFacade
) : ModelBaseImpl(),
    PaymentsModel {

    override var currentPeriod: PaymentsListPeriodInfo = PaymentsListPeriodInfo(ZonedDateTime.now(), true)
        private set

    override suspend fun getPaymentsList(): ModelCallResult<out PaymentsList> =
        getValue {
            val hasAccounts = db.readAccounts().isNotEmpty()

            if(!hasAccounts) {
                PaymentsList(hasAccounts, listOf())
            } else {
                PaymentsList(hasAccounts, listOf())
            }
        }

    override fun moveToNextPeriod(): PaymentsListPeriodInfo {
        val current = currentPeriod.dateInPeriod
        val now = ZonedDateTime.now()

        if(now.monthValue == current.monthValue && now.year == current.year) {
            return currentPeriod
        }

        val newCurrent = currentPeriod.dateInPeriod.plusMonths(1)
        val isLastPeriod = now.monthValue == newCurrent.monthValue && now.year == newCurrent.year

        currentPeriod = PaymentsListPeriodInfo(newCurrent, isLastPeriod)
        return currentPeriod
    }

    override fun moveToPriorPeriod(): PaymentsListPeriodInfo {
        currentPeriod = PaymentsListPeriodInfo(currentPeriod.dateInPeriod.minusMonths(1), false)
        return currentPeriod
    }
}