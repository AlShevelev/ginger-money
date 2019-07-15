package com.syleiman.gingermoney.dto.entities

import com.syleiman.gingermoney.core.global_entities.money.Money
import org.threeten.bp.ZonedDateTime

data class Payment (
    val id: Long?,

    val account: Account,

    val paymentCategoryId: Long,

    val amount: Money,

    val memo: String?,

    val createAt: ZonedDateTime,

    val createAtEstimate: Int
)