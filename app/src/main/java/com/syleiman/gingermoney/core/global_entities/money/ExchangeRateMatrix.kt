package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.core.global_entities.collections.DoubleRectArray

/**
 * All possible exchange rates as in-memory matrix
 * @param baseCurrency - base selecedCurrency to matrix calculation (it may be any selecedCurrency)
 * @param exchangeRates - set of exchange rates for every selecedCurrency (except a [baseCurrency] one) to a [baseCurrency].
 */
class ExchangeRateMatrix(private val baseCurrency: Currency, private val exchangeRates: List<ExchangeRate>) {

    private val totalCurrencies = Currency.values().size

    // The matrix as a linear array
    private val matrix = DoubleRectArray(totalCurrencies, totalCurrencies)

    init {
        validateSourceData()
        calculateMatrix()
    }

    /**
     *
     */
    fun getExchangeRate(from: Currency, to: Currency) = ExchangeRate(from, to, getValue(from, to))

    /**
     *
     */
    private fun validateSourceData() {
        if(exchangeRates.size != Currency.values().size-1) {
            throw IncorrectMoneyOperationException("Invalid length of an exchange rates list")
        }

        val processedCurrency = mutableSetOf<Currency>()

        exchangeRates.forEach { exchangeRate ->
            if(exchangeRate.quoteFactor <= 0.0) {
                throw IncorrectMoneyOperationException("A value of a quote must be positive")
            }

            if(exchangeRate.to != baseCurrency) {
                throw IncorrectMoneyOperationException("Invalid target value: ${exchangeRate.to}")
            }

            if(exchangeRate.from == baseCurrency) {
                throw IncorrectMoneyOperationException("Invalid source value: ${exchangeRate.from}")
            }

            if(processedCurrency.contains(exchangeRate.from)) {
                throw IncorrectMoneyOperationException("Source value (${exchangeRate.from}) must be unique")
            }
            processedCurrency.add(exchangeRate.from)
        }
    }

    /**
     *
     */
    private fun calculateMatrix() {
        // Set quote for a base selecedCurrency
        setValue(baseCurrency, baseCurrency, 1.0)

        exchangeRates.forEach { exchangeRate1 ->
            // Normal and reverse value to a base selecedCurrency
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

    /**
     *
     */
    private fun getValue(from: Currency, to: Currency): Double = matrix.get(from.value.toInt(), to.value.toInt())

    /**
     *
     */
    private fun setValue(from: Currency, to: Currency, value: Double) = matrix.set(from.value.toInt(), to.value.toInt(), value)
}

