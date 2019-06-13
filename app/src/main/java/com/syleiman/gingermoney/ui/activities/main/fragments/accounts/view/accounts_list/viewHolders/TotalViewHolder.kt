package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders

import android.view.*
import androidx.appcompat.widget.PopupMenu
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.TotalListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_accounts_total_list_item.view.*

class TotalViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_accounts_total_list_item, parentView, false)
) {

    private var eventsProcessor: ListItemEventsProcessor? = null

    private var popupMenu: PopupMenu? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        (listItem as TotalListItem)
            .let {
                itemView.amount.text = MoneyHardCentsFormatter().format(it.amount)
            }

        itemView.menuButton.setOnClickListener { createMenu(it) }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null

        itemView.menuButton.setOnClickListener(null)
        popupMenu?.dismiss()
    }

    private fun createMenu(view: View) {
        popupMenu = PopupMenu(view.context, view)
            .apply {
                val inflater: MenuInflater = this.menuInflater
                inflater.inflate(R.menu.add_edit_account_total, this.menu)
                this.show()
            }
    }
}