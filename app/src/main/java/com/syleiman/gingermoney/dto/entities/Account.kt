package com.syleiman.gingermoney.dto.entities

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup

data class Account (
    var id: Long?,

    val accountGroup: AccountGroup,

    val name: String,

    val amount: Money,

    val memo: String?
)