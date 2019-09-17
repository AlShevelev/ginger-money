package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import org.threeten.bp.ZonedDateTime

data class DayListItem(
    override val id: Long,
    val day: ZonedDateTime,
    val isWeekend: Boolean,
    val amount: Money
): ListItem