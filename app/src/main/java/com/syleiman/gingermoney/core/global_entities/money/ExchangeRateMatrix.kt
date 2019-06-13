package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.core.global_entities.collections.DoubleRectArray

/**
 * All possible exchange rates as in-memory matrix
 * @param exchangeRates - set of exchange rates for every currency.
 */
class ExchangeRateMatrix(private val exchangeRates: List<ExchangeRate>) {

    private val totalCurrencies = Currency.values().size

    // The matrix as a linear array
    private val matrix = DoubleRectArray(totalCurrencies, totalCurrencies)

    init {
        calculateMatrix()
    }

    fun getExchangeRate(from: Currency, to: Currency) = ExchangeRate(from, to, getValue(from, to))

    private fun calculateMatrix() {
        // Set quote for a base currency
        setValue(exchangeRates[0].to, exchangeRates[0].to, 1.0)

        exchangeRates.forEach { exchangeRate1 ->
            // Normal and reverse value to a base currency
            setValue(exchangeRate1.from, exchangeRate1.to, exchangeRate1.quoteFactor)
            setValue(exchangeRate1.to, exchangeRate1.from, 1/exchangeRate1.quoteFactor)

            // Other pairs
            exchangeRates.forEach { exchangeRate2 ->
                val quoteFactor = if(exchangeRate1.from == exchangeRate2.from) {
                    1.0
                } else {
                    1 / (exchangeRate2.quoteFactor / exchangeRate1.quoteFactor)
                }
                setValue(exchangeRate1.from, exchangeRate2.from, quoteFactor)
            }
        }
    }

    private fun getValue(from: Currency, to: Currency): Double = matrix.get(from.value.toInt(), to.value.toInt())

    private fun setValue(from: Currency, to: Currency, value: Double) = matrix.set(from.value.toInt(), to.value.toInt(), value)
}

