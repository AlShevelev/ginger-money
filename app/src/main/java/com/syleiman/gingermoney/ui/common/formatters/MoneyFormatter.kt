package com.syleiman.gingermoney.ui.common.formatters

import com.syleiman.gingermoney.core.global_entities.money.Money

/**
 *
 */
interface MoneyFormatter {
    /**
     *
     */
    fun format(value: Money): String
}