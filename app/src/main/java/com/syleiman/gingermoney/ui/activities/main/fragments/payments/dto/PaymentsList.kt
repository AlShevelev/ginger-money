package com.syleiman.gingermoney.ui.activities.main.fragments.payments.dto

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class PaymentsList (
    val hasAccounts: Boolean,
    val payments: List<ListItem>
)