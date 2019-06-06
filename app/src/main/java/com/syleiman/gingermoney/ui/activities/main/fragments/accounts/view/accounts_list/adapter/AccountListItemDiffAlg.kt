package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view.accounts_list.adapter

import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.AccountListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.GroupListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto.TotalListItem
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase

class AccountListItemDiffAlg(
    oldList: List<ListItem>,
    newList: List<ListItem>
) : DiffAlgBase<ListItem>(oldList, newList) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when {
            oldItem is AccountListItem && newItem is AccountListItem -> {
                oldItem.dbId == newItem.dbId &&
                oldItem.amount == newItem.amount &&
                oldItem.name == newItem.name
            }

            oldItem is GroupListItem && newItem is GroupListItem ->
                oldItem.foregroundColor == newItem.foregroundColor &&
                oldItem.backgroundColor == newItem.backgroundColor &&
                oldItem.accountGroup == newItem.accountGroup &&
                oldItem.amount == newItem.amount

            oldItem is TotalListItem && newItem is TotalListItem -> oldItem.amount == newItem.amount

            else -> false
        }
    }
}