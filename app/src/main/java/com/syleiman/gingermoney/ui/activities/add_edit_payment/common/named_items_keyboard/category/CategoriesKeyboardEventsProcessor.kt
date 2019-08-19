package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.category

import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsKeyboardEventsProcessor

interface CategoriesKeyboardEventsProcessor: NamedItemsKeyboardEventsProcessor {
    fun onCategorySelect(id: Long)

    fun onCloseCategoryKeyboard()

    fun onEditCategories()
}