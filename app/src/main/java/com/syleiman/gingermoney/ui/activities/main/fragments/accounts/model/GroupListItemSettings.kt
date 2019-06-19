package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors

data class GroupListItemSettings (
    val currency: Currency,
    val colors: TextColors
)