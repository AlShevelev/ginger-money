package com.syleiman.gingermoney.dto.entities

import org.threeten.bp.ZonedDateTime

data class PaymentCategory(
    val id: Long?,
    val name: String,
    val createAt: ZonedDateTime,
    val lastUsed: ZonedDateTime?
)

val PaymentCategory.sortDate: ZonedDateTime
    get() = this.lastUsed ?: createAt