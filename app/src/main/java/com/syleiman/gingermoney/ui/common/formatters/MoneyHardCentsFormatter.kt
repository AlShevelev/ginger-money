package com.syleiman.gingermoney.ui.common.formatters

import com.syleiman.gingermoney.core.global_entities.money.Money
import java.lang.UnsupportedOperationException
import java.text.DecimalFormat

/**
 * Converts [Money] to String with mandatory cents. Locale-independent.
 */
open class MoneyHardCentsFormatter: MoneyFormatter {
    // to avoid runtime template construction
    private companion object {
        const val CENTS_2_ZERO_FORMAT_STRING = "0.00"
        const val CENTS_2_FORMAT_STRING = "#,###.00"
    }

    /**
     *
     */
    override fun format(value: Money): String {
        val numberValue = Math.abs(value.value)

        val numberFormatTemplate = when (value.currency.centDigits) {
            2 -> {
                if(numberValue < 1.0) {
                    getTemplateForZero()
                } else {
                    getTemplateForNonZero()
                }
            }
            else -> throw UnsupportedOperationException("This quantity of cent digits is not supported: ${value.currency.centDigits}")
        }

        val sign = if(value.value < 0.0) "-" else ""

        return "${value.currency.symbol} $sign${DecimalFormat(numberFormatTemplate).format(numberValue)}"
    }

    /**
     *
     */
    protected open fun getTemplateForZero() = CENTS_2_ZERO_FORMAT_STRING

    /**
     *
     */
    protected open fun getTemplateForNonZero() = CENTS_2_FORMAT_STRING
}