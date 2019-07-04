package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.add_payment.model

import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import org.threeten.bp.ZonedDateTime

interface AddPaymentModel: ModelBase {
    fun getCreateAt(): ZonedDateTime
}