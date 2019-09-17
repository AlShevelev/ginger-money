package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.toHardCentsString
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.PaymentListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_payments_payment_list_item.view.*

class PaymentViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_payments_payment_list_item, parentView, false)
) {
    private var eventsProcessor: ListItemEventsProcessor? = null

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        this.eventsProcessor = listItemEventsProcessor

        (listItem as PaymentListItem)
            .let { paymentListItem ->
                itemView.categoryName.text = paymentListItem.categoryName
                itemView.accountName.text = paymentListItem.accountName
                itemView.amount.text = paymentListItem.amount.toHardCentsString()

                itemView.setOnClickListener { listItemEventsProcessor.onPaymentClick(paymentListItem.id) }
            }
    }

    /**
     * Used resources of UI elements must be released here (called in ListAdapterBase::onViewRecycled)
     */
    override fun release() {
        eventsProcessor = null
        itemView.setOnClickListener(null)
    }
}
