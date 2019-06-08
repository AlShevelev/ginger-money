package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface

/**
 * Class for saving, loading, validating and recalculating source data for [ExchangeRateMatrix]
 */
class ExchangeRateSourceData(private val db: DbStorageFacadeInterface) {

    /**
     * Updates source rates data in the Db
     */
    fun updateRates(rates: List<ExchangeRate>) = db.updateSourceExchangeRates(rates)

    /**
     * Returns list of rates
     */
    fun getRates(baseCurrency: Currency): List<ExchangeRate> {
        val sourceRates = db.getSourceExchangeRates()
            .takeIf { it.isNotEmpty() }
            ?: getDefaultData()

        val recalculationNeeded = validate(sourceRates, baseCurrency)

        return if(recalculationNeeded) recalculate(sourceRates, baseCurrency) else sourceRates
    }

    /**
     * Validate set of rates
     * @return true if rates need to be recalculated
     */
    private fun validate(rates: List<ExchangeRate>, baseCurrency: Currency): Boolean {
        if(rates.size != Currency.values().size-1) {
            throw IncorrectMoneyOperationException("Invalid length of an exchange rates list")
        }

        val fromSet = mutableSetOf<Currency>()
        val toSet = mutableSetOf<Currency>()

        var recalculationNeeded = false

        rates.forEach { exchangeRate ->
            if(exchangeRate.quoteFactor <= 0.0) {
                throw IncorrectMoneyOperationException("A value of a quote must be positive")
            }

            if(exchangeRate.to != baseCurrency || exchangeRate.from == baseCurrency) {
                recalculationNeeded = true
            }

            if(fromSet.contains(exchangeRate.from)) {
                throw IncorrectMoneyOperationException("Source value (${exchangeRate.from}) must be unique")
            }

            fromSet.add(exchangeRate.from)
            toSet.add(exchangeRate.to)
        }

        if(toSet.size != 1) {
            throw IncorrectMoneyOperationException("Target values must be the same")
        }

        return recalculationNeeded
    }

    /**
     * Recalculates the rates to new base currency
     */
    private fun recalculate(rates: List<ExchangeRate>, newBaseCurrency: Currency): List<ExchangeRate> {
        val newBaseCurrencyIndex = rates.indexOfFirst { it.from == newBaseCurrency }
        val baseFactor = 1 / rates[newBaseCurrencyIndex].quoteFactor

        return rates
            .filterIndexed { i, _ -> i != newBaseCurrencyIndex  }
            .map { ExchangeRate(it.from, newBaseCurrency, it.quoteFactor * baseFactor) }
            .plus(ExchangeRate(rates[newBaseCurrencyIndex].to, newBaseCurrency, baseFactor))
    }

    /**
     * Returns exchange rates if source data is empty
     */
    private fun getDefaultData(): List<ExchangeRate> =
        listOf(
            ExchangeRate(Currency.RUB, Currency.USD, 65.0),
            ExchangeRate(Currency.EUR, Currency.USD, 0.87))
}