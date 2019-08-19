package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.view_holder_select_item_list_item.view.*

abstract class NamedItemViewHolderBase<T: NamedItemsKeyboardEventsProcessor>(
    parentView: ViewGroup
) : ViewHolderBase<T, NamedListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.view_holder_select_item_list_item, parentView, false)
) {

    private var eventsProcessor: NamedItemsKeyboardEventsProcessor? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: NamedListItem, listItemEventsProcessor: T) {
        this.eventsProcessor = listItemEventsProcessor

        itemView.accountButton.text = listItem.name

        setItemOnClickListener(itemView.accountButton, listItemEventsProcessor, listItem)
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null
        itemView.accountButton.setOnClickListener(null)
    }

    protected abstract fun setItemOnClickListener(button: Button, eventsProcessor: T, listItem: NamedListItem)
}