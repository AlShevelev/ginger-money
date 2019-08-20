package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.list

import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase

class ListCategoriesDiffAlg(
    oldList: List<CategoryListItem>,
    newList: List<CategoryListItem>
) : DiffAlgBase<CategoryListItem>(oldList, newList) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.name == newItem.name && oldItem.canDelete == newItem.canDelete
    }
}