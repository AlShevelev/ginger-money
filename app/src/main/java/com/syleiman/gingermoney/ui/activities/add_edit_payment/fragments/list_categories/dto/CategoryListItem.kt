package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class CategoryListItem(
    override val id: Long,
    val name: String,
    val canDelete: Boolean
): ListItem