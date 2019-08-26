package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto

import org.threeten.bp.ZonedDateTime

data class PaymentsListPeriodInfo(
    val dateInPeriod: ZonedDateTime,
    val isLastPeriod: Boolean
)