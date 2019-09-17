package com.syleiman.gingermoney.ui.activities.main.fragments.payments.view.payments_list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.date_time.DateTimeFormat
import com.syleiman.gingermoney.core.global_entities.date_time.format
import com.syleiman.gingermoney.core.global_entities.money.toHardCentsString
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dependency_injection.PaymentsFragmentComponent
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto.DayListItem
import com.syleiman.gingermoney.ui.activities.main.fragments.payments.view_model.ListItemEventsProcessor
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.recycler_view.ViewHolderBase
import kotlinx.android.synthetic.main.fragment_main_payments_day_list_item.view.*
import javax.inject.Inject

class DayViewHolder(
    parentView: ViewGroup
) : ViewHolderBase<ListItemEventsProcessor, ListItem>(
    LayoutInflater.from(parentView.context).inflate(R.layout.fragment_main_payments_day_list_item, parentView, false)
) {
    @Inject
    internal lateinit var appResourcesProvider: AppResourcesProvider

    init {
        App.injections.get<PaymentsFragmentComponent>().inject(this)
    }

    /**
     * UI elements must be initialized here
     */
    override fun init(listItem: ListItem, listItemEventsProcessor: ListItemEventsProcessor) {
        (listItem as DayListItem)
            .let { dayListItem ->
                itemView.dayNumber.text = dayListItem.day.dayOfMonth.toString()
                itemView.amount.text = dayListItem.amount.toHardCentsString()
                itemView.dayAndMonth.text = dayListItem.day.format(DateTimeFormat.MONTH_AND_YEAR, appResourcesProvider)

                itemView.dayOfWeek.text = appResourcesProvider.getDayOfWeekString(dayListItem.day.dayOfWeek)
                itemView.dayOfWeek.background = if(dayListItem.isWeekend) {
                    appResourcesProvider.getDrawable(R.drawable.bcg_payments_list_weekend)
                } else {
                    appResourcesProvider.getDrawable(R.drawable.bcg_payments_list_weekday)
                }
            }
    }
}