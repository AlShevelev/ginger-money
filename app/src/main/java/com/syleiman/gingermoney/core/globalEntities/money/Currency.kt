package com.syleiman.gingermoney.core.globalEntities.money

/**
 *
 */
enum class Currency(val value: Byte) {
    USD(0),
    EUR(1),
    RUB(2);

    companion object Create {
        fun from(sourceValue: Byte): Currency = values().first { it.value == sourceValue }
        fun from(sourceValue: String): Currency = values().first { it.toString() == sourceValue }
    }
}

/**
 * @param totalCents Quantity of cents in the value
 */
fun Currency.toMoney(totalCents: Long) = Money(totalCents, this)

/**
 * @param value The value with cents in a fraction
 */
fun Currency.toMoney(value: Double) = Money(value, this)

/**
 *
 */
fun Currency.toExchangeRate(to: Currency, quoteRate: Double) = ExchangeRate(this, to, quoteRate)