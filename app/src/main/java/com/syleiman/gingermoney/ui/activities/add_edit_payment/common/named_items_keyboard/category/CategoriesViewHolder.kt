package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category

import android.view.ViewGroup
import android.widget.Button
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemViewHolderBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem

class CategoriesViewHolder(parentView: ViewGroup): NamedItemViewHolderBase<CategoriesKeyboardEventsProcessor>(parentView) {
    override fun setItemOnClickListener(
        button: Button,
        eventsProcessor: CategoriesKeyboardEventsProcessor,
        listItem: NamedListItem) =
        button.setOnClickListener { eventsProcessor.onCategorySelect(listItem.id) }
}