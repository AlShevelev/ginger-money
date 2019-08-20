package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.list

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListAdapterBase
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase

class ListCategoriesAdapter(
    listItemEventsProcessor: ListItemEventsProcessor
) : ListAdapterBase<ListItemEventsProcessor, CategoryListItem>(
    listItemEventsProcessor) {

    override fun createDiffAlg(oldData: List<CategoryListItem>, newData: List<CategoryListItem>): DiffAlgBase<CategoryListItem> =
       ListCategoriesDiffAlg(oldData, newData)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<ListItemEventsProcessor, CategoryListItem> =
        CategoryViewHolder(parent)

    override fun getItemViewType(position: Int): Int = 0
}