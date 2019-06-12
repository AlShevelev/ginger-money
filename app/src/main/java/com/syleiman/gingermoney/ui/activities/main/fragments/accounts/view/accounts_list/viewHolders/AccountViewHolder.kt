package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.AccountListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_accounts_account_list_item.view.*

class AccountViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_accounts_account_list_item, parentView, false)
) {

    private var eventsProcessor: ListItemEventsProcessor? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        (listItem as AccountListItem)
            .let {
                itemView.name.text = it.name
                itemView.amount.text = MoneyHardCentsFormatter().format(it.amount)
            }

        itemView.setOnClickListener { eventsProcessor?.onAccountClick(listItem.dbId) }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null
        itemView.setOnClickListener(null)
    }
}