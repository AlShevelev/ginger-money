package com.syleiman.gingermoney.ui.common.widgets.amount_keyboard

import com.syleiman.gingermoney.core.global_entities.money.Money

data class AmountKeyboardEditingResult(
    val value: Money,
    val hasCents: Boolean
)