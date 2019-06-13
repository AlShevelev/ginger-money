package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.Color

data class GroupListItemSettings (
    val currency: Currency,
    val foregroundColor: Color,
    val backgroundColor: Color
)