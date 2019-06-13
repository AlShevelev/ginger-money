package com.syleiman.gingermoney.dto.entities

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color

data class AccountGroupSettings(
    /**
     * Null for Total group
     */
    val accountGroup: AccountGroup?,

    val currency: Currency?,

    val foregroundColor: Color?,

    val backgroundColor: Color?
)