package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model

import com.syleiman.gingermoney.dto.enums.AccountGroup

interface ListItemEventsProcessor {
    fun onAccountClick(accountDbId: Long)

    fun onOnCurrencyMenuItemClick(group: AccountGroup?)

    fun onOnColorMenuItemClick(group: AccountGroup)
}