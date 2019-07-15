package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.common.recycler_view.StaticListAdapterBase
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase

class AccountsListAdapter(
    listItemEventsProcessor: AccountsKeyboardEventsProcessor,
    items: List<AccountListItem>
) : StaticListAdapterBase<AccountsKeyboardEventsProcessor, AccountListItem>(
    listItemEventsProcessor,
    items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<AccountsKeyboardEventsProcessor, AccountListItem> =
        AccountViewHolder(parent)

    override fun getItemViewType(position: Int): Int = 0
}