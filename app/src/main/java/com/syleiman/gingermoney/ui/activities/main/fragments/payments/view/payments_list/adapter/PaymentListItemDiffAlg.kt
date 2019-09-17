package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.adapter

import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.DayListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.TotalListItem
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

class PaymentListItemDiffAlg(
    oldList: List<ListItem>,
    newList: List<ListItem>
) : DiffAlgBase<ListItem>(oldList, newList) {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when {
            oldItem is TotalListItem && newItem is TotalListItem -> oldItem.amount == newItem.amount

            oldItem is DayListItem && newItem is DayListItem -> oldItem.amount == newItem.amount && oldItem.day == newItem.day

            oldItem is PaymentListItem && newItem is PaymentListItem ->
                oldItem.amount == newItem.amount &&
                oldItem.accountName == newItem.accountName &&
                oldItem.categoryName == newItem.categoryName

            else -> false
        }
    }
}