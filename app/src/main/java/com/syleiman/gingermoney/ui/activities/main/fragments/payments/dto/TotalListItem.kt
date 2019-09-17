package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class TotalListItem(
    override val id: Long,
    val amount: Money
): ListItem