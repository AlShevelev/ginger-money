package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.dto.CategoryListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.list_categories.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_add_edit_payment_list_categories_item.view.*
import kotlinx.android.synthetic.main.fragment_main_accounts_group_list_item.view.name

class CategoryViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, CategoryListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_add_edit_payment_list_categories_item, parentView, false)
) {

    private var eventsProcessor: ListItemEventsProcessor? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: CategoryListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        itemView.name.text = listItem.name
        itemView.deleteButton.isEnabled = listItem.canDelete

        itemView.setOnClickListener { eventsProcessor?.onCategoryClick(listItem.id) }
        itemView.deleteButton.setOnClickListener { eventsProcessor?.onDeleteCategoryClick(listItem.id) }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null
        itemView.setOnClickListener(null)
    }
}