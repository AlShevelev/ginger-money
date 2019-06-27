package com.syleiman.gingermoney.ui.activities.main.fragments.payments.model

import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentsList
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface PaymentsModel: ModelBase {
    suspend fun getPaymentsList(): ModelCallResult<out PaymentsList>
}