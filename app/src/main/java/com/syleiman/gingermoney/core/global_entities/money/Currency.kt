package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.core.helpers.MathEx
import java.util.Currency as JavaCurrency

/**
 * @param symbol - locale-independent currency symbol (from here: https://www.xe.com/symbols.php)
 */
enum class Currency(val value: Byte, val symbol: Char) {
    USD(0, '\u0024'),
    EUR(1, '\u20ac'),
    RUB(2, '\u20bd');

    /**
     * Quantity of cents digits in one unit of the currency
     */
    val centDigits: Int
    get() = getJavaCurrency().defaultFractionDigits

    /**
     * How many cents in a one unit of the currency
     */
    val centsFactor: Int
    get() = MathEx.pow10(centDigits).toInt()

    companion object Create {
        fun from(sourceValue: Byte): Currency = values().first { it.value == sourceValue }
        fun from(sourceValue: String): Currency = values().first { it.toString() == sourceValue }
    }

    /**
     * @param totalCents Quantity of cents in the value
     */
    fun toMoney(totalCents: Long) = Money(totalCents, this)

    /**
     * @param value The value with cents in a fraction
     */
    fun toMoney(value: Double) = Money(value, this)

    fun toExchangeRate(to: Currency, quoteRate: Double) = ExchangeRate(this, to, quoteRate)

    private fun getJavaCurrency(): JavaCurrency = JavaCurrency.getInstance(this.toString())
}