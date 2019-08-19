package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.common.recycler_view.StaticListAdapterBase

abstract class NamedItemsListAdapterBase<T: NamedItemsKeyboardEventsProcessor> (
    listItemEventsProcessor: T,
    items: List<NamedListItem>
) : StaticListAdapterBase<T, NamedListItem>(
    listItemEventsProcessor,
    items
) {
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedItemViewHolderBase<T>

    override fun getItemViewType(position: Int): Int = 0
}