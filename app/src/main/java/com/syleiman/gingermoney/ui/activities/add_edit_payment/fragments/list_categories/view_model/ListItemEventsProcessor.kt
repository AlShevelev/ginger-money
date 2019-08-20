package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model

interface ListItemEventsProcessor {
    fun onCategoryClick(categoryDbId: Long)

    fun onDeleteCategoryClick(categoryDbId: Long)
}