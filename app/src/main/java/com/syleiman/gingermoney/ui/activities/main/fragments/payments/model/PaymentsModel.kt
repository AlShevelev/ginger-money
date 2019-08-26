package com.syleiman.gingermoney.ui.activities.main.fragments.payments.model

import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsList
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsListPeriodInfo
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface PaymentsModel: ModelBase {
    val currentPeriod: PaymentsListPeriodInfo

    suspend fun getPaymentsList(): ModelCallResult<out PaymentsList>

    fun moveToNextPeriod(): PaymentsListPeriodInfo

    fun moveToPriorPeriod(): PaymentsListPeriodInfo
}