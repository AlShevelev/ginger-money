package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account

import android.view.ViewGroup
import android.widget.Button
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemViewHolderBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem

class AccountsViewHolder(parentView: ViewGroup): NamedItemViewHolderBase<AccountsKeyboardEventsProcessor>(parentView) {
    override fun setItemOnClickListener(
        button: Button,
        eventsProcessor: AccountsKeyboardEventsProcessor,
        listItem: NamedListItem) =
        button.setOnClickListener { eventsProcessor.onAccountSelect(listItem.id) }
}