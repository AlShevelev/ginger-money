package com.syleiman.gingermoney.ui.common.formatters

import com.syleiman.gingermoney.core.global_entities.money.Money

/**
 * Converts [Money] to String with optional cents. Locale-independent.
 */
class MoneySoftCentsFormatter: MoneyHardCentsFormatter() {
    // to avoid runtime template construction
    companion object {
        const val CENTS_2_ZERO_FORMAT_STRING = "0.##"
        const val CENTS_2_FORMAT_STRING = "#,###.##"
    }

    override fun getTemplateForZero() = CENTS_2_ZERO_FORMAT_STRING

    override fun getTemplateForNonZero() = CENTS_2_FORMAT_STRING
}