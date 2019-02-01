package com.syleiman.gingermoney.core.globalEntities.money

import java.util.Currency
import com.syleiman.gingermoney.core.globalEntities.money.Currency as CurrencyCode

/**
 * Money totalCents with a currency
 */
class Money {
    /**
     * Quantity of cents in the value
     */
    val totalCents: Long

    /**
     * How many cents in a one unit of money
     */
    val centsFactor: Int

    /**
     *
     */
    val currency: CurrencyCode

    /**
     * The value with cents in a fraction
     */
    val value: Double
    get() = totalCents.toDouble() / centsFactor

    /**
     *
     * @param totalCents Quantity of cents in the value
     */
    constructor(totalCents: Long, currency: CurrencyCode) {
        val currencyDescription = createCurrencyDescription(currency)

        this.currency = currency
        centsFactor = calculateCentFactor(currencyDescription)

        this.totalCents = totalCents
    }

    /**
     *
     * @param value The value with cents in a fraction
     */
    constructor(value: Double, currency: CurrencyCode) {
        val currencyDescription = createCurrencyDescription(currency)

        this.currency = currency
        centsFactor = calculateCentFactor(currencyDescription)

        this.totalCents = Math.round(value * centsFactor)
    }

    /**
     *
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val money = other as Money

        if (totalCents != money.totalCents) {
            return false
        }

        if(currency != money.currency) {
            return false
        }

        return true
    }

    /**
     *
     */
    override fun hashCode(): Int {
        var result = (totalCents xor totalCents.ushr(32)).toInt()
        result = 31 * result + currency.hashCode()
        return result
    }

    /**
     * Add one [Money] instance to other [Money] instance. The currency must be same.
     */
    operator fun plus(otherMoney: Money): Money {
        checkOperationPossibility(otherMoney)
        return Money(
            totalCents + otherMoney.totalCents,
            this.currency
        )
    }

    /**
     * Subtract one [Money] instance from other [Money] instance. The currency must be same.
     */
    operator fun minus(otherMoney: Money): Money {
        checkOperationPossibility(otherMoney)
        return Money(
            totalCents - otherMoney.totalCents,
            this.currency
        )
    }

    /**
     * Convert a [Money] value to another value with a different currency
     */
    fun convertTo(rate: ExchangeRate): Money {
        checkExchangePossibility(rate)
        return Money(value / rate.quoteFactor, rate.to)
    }

    /**
     *
     */
    fun compareTo(other: Any): Int {
        return compareTo(other as Money)
    }

    /**
     * Compare two money values
     */
    operator fun compareTo(otherMoney: Money): Int {
        checkOperationPossibility(otherMoney)
        return totalCents.compareTo(otherMoney.totalCents)
    }

    /**
     *
     */
    private fun calculateCentFactor(currencyDescription: Currency): Int {
        return Math.pow(10.0, currencyDescription.defaultFractionDigits.toDouble()).toInt()
    }

    /**
     *
     */
    private fun createCurrencyDescription(currency: CurrencyCode) =
        when(currency) {
            CurrencyCode.USD -> Currency.getInstance("USD")
            CurrencyCode.EUR -> Currency.getInstance("EUR")
            CurrencyCode.RUB -> Currency.getInstance("RUB")
        }

    /**
     * Check possibility of an operation between [otherMoney] and this money object
     */
    private fun checkOperationPossibility(otherMoney: Money) {
        if(currency != otherMoney.currency) {
            throw IncorrectMoneyOperationException("Operations between different currencies are not supported")
        }
    }

    /**
     *
     */
    private fun checkExchangePossibility(rate: ExchangeRate) {
        if(currency != rate.from) {
            throw IncorrectMoneyOperationException("The Operation is possible only between the same currencies")
        }

        if(rate.from == rate.to) {
            throw IncorrectMoneyOperationException("Invalid exchange rate - you can't convert the currency to the same currency")
        }

        if(rate.quoteFactor < 0) {
            throw IncorrectMoneyOperationException("Invalid exchange rate - quote rate can't be negative")
        }
    }
}