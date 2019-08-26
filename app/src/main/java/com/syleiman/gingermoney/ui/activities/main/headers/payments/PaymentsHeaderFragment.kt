package com.syleiman.gingermoney.ui.activities.main.headers.payments

import android.view.View
import org.threeten.bp.ZonedDateTime

interface PaymentsHeaderFragment {
    fun setOnNextMonthButtonClickListener(listener: View.OnClickListener?)

    fun setOnPriorMonthButtonClickListener(listener: View.OnClickListener?)

    fun setDateToDisplay(date: ZonedDateTime, isLastPeriod: Boolean)
}