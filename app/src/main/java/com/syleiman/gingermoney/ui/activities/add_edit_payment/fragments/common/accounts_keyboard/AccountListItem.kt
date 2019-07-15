package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class AccountListItem(
    override val id: Long,
    val name: String
): ListItem