package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.toHardCentsString
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.TotalListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_payments_total_list_item.view.*

class TotalViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_payments_total_list_item, parentView, false)
) {
    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        (listItem as TotalListItem)
            .let { paymentListItem ->
                itemView.amount.text = paymentListItem.amount.toHardCentsString()
            }
    }
}
