package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.adapter

import android.view.ViewGroup
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.DayListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.TotalListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders.DayViewHolder
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders.PaymentViewHolder
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders.TotalViewHolder
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders.ViewHolderType
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.DiffAlgBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListAdapterBase
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase

class PaymentsListAdapter(
    listItemEventsProcessor: ListItemEventsProcessor
) : ListAdapterBase<ListItemEventsProcessor, ListItem>(
    listItemEventsProcessor) {

    override fun createDiffAlg(oldData: List<ListItem>, newData: List<ListItem>): DiffAlgBase<ListItem> =
        PaymentListItemDiffAlg(oldData, newData)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<ListItemEventsProcessor, ListItem> =
        when(viewType) {
            ViewHolderType.DAY -> DayViewHolder(parent)
            ViewHolderType.PAYMENT -> PaymentViewHolder(parent)
            ViewHolderType.TOTAL -> TotalViewHolder(parent)
            else -> throw UnsupportedOperationException("This type of view is not supported: $viewType")
        }

    override fun getItemViewType(position: Int): Int =
        when(items[position]) {
            is DayListItem -> ViewHolderType.DAY
            is PaymentListItem -> ViewHolderType.PAYMENT
            is TotalListItem -> ViewHolderType.TOTAL
            else -> throw UnsupportedOperationException("This type of item is not supported")
        }
}