package com.syleiman.gingermoney.core.global_entities.money

/**
 * Money totalCents with a selected Currency
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
    get() = currency.centsFactor

    val currency: Currency

    /**
     * The value with cents in a fraction
     */
    val value: Double
    get() = totalCents.toDouble() / centsFactor

    /**
     *
     * @param totalCents Quantity of cents in the value
     */
    constructor(totalCents: Long, currency: Currency) {
        this.currency = currency

        this.totalCents = totalCents
    }

    /**
     *
     * @param value The value with cents in a fraction
     */
    constructor(value: Double, currency: Currency) {
        this.currency = currency

        this.totalCents = Math.round(value * centsFactor)
    }

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

        return if(rate.from == rate.to) {
            Money(this.totalCents, this.currency)
        } else {
            Money(value / rate.quoteFactor, rate.to)
        }
    }


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
     * Check possibility of an operation between [otherMoney] and this money object
     */
    private fun checkOperationPossibility(otherMoney: Money) {
        if(currency != otherMoney.currency) {
            throw IncorrectMoneyOperationException("Operations between different currencies are not supported")
        }
    }

    private fun checkExchangePossibility(rate: ExchangeRate) {
        if(currency != rate.from) {
            throw IncorrectMoneyOperationException("The Operation is possible only between the same currencies")
        }

        if(rate.quoteFactor < 0) {
            throw IncorrectMoneyOperationException("Invalid exchange rate - quote rate can't be negative")
        }
    }
}