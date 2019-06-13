package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup

data class ListItemsSettings (
    val totalCurrency: Currency,
    val groupsSettings: Map<AccountGroup, GroupListItemSettings>
)