package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemViewHolderBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsListAdapterBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem

class AccountsListAdapterBase (
    listItemEventsProcessor: AccountsKeyboardEventsProcessor,
    items: List<NamedListItem>
) : NamedItemsListAdapterBase<AccountsKeyboardEventsProcessor>(
    listItemEventsProcessor,
    items
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedItemViewHolderBase<AccountsKeyboardEventsProcessor> =
        AccountsViewHolder(parent)

    override fun getItemViewType(position: Int): Int = 0
}