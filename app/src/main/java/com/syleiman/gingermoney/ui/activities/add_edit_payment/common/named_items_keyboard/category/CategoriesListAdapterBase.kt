package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemViewHolderBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsListAdapterBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem

class CategoriesListAdapterBase (
    listItemEventsProcessor: CategoriesKeyboardEventsProcessor,
    items: List<NamedListItem>
) : NamedItemsListAdapterBase<CategoriesKeyboardEventsProcessor>(
    listItemEventsProcessor,
    items
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedItemViewHolderBase<CategoriesKeyboardEventsProcessor> =
        CategoriesViewHolder(parent)

    override fun getItemViewType(position: Int): Int = 0
}