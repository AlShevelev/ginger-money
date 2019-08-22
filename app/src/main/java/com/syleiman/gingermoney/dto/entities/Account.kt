package com.syleiman.gingermoney.dto.entities

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import org.threeten.bp.ZonedDateTime

data class Account (
    val id: Long?,

    val accountGroup: AccountGroup,

    val name: String,

    val amount: Money,

    val memo: String?,

    val createAt: ZonedDateTime,

    val lastUsed: ZonedDateTime?
)

val Account.sortDate: ZonedDateTime
    get() = this.lastUsed ?: createAt