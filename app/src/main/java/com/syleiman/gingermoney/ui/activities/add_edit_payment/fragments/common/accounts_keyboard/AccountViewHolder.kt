package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.view_holder_accounts_category_list_item.view.*

class AccountViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<AccountsKeyboardEventsProcessor, AccountListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.view_holder_accounts_category_list_item, parentView, false)
) {

    private var eventsProcessor: AccountsKeyboardEventsProcessor? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: AccountListItem, listItemEventsProcessor: AccountsKeyboardEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        itemView.accountButton.text = listItem.name

        itemView.accountButton.setOnClickListener { eventsProcessor?.onAccountItemClick(listItem.id) }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null
        itemView.accountButton.setOnClickListener(null)
    }
}A