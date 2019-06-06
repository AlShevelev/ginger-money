package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.AccountListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.TotalListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.viewHolders.*
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListAdapterBase
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import java.lang.UnsupportedOperationException

class AccountsListAdapter(
    listItemEventsProcessor: ListItemEventsProcessor
) : ListAdapterBase<ListItemEventsProcessor, ListItem>(
    listItemEventsProcessor) {

    override fun createDiffAlg(oldData: List<ListItem>, newData: List<ListItem>): DiffAlgBase<ListItem> =
        AccountListItemDiffAlg(oldData, newData)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<ListItemEventsProcessor, ListItem> =
        when(viewType) {
            ViewHolderType.ACCOUNT -> AccountViewHolder(parent)
            ViewHolderType.GROUP -> GroupViewHolder(parent)
            ViewHolderType.TOTAL -> TotalViewHolder(parent)
            else -> throw UnsupportedOperationException("This type of view is not supported: $viewType")
        }

    override fun getItemViewType(position: Int): Int =
        when(items[position]) {
            is AccountListItem -> ViewHolderType.ACCOUNT
            is GroupListItem -> ViewHolderType.GROUP
            is TotalListItem -> ViewHolderType.TOTAL
            else -> throw UnsupportedOperationException("This type of item is not supported")
        }
}