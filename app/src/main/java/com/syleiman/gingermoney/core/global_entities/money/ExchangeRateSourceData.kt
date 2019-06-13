package com.syleiman.gingermoney.core.global_entities.money

import com.syleiman.gingermoney.core.storages.db.facade.DbStorageFacadeInterface

/**
 * Class for saving, loading and validating source data for [ExchangeRateMatrix]
 */
class ExchangeRateSourceData(private val db: DbStorageFacadeInterface) {

    /**
     * Updates source rates data in the Db
     */
    fun updateRates(rates: List<ExchangeRate>) = db.updateSourceExchangeRates(rates)

    /**
     * Returns list of rates
     */
    fun getRates(): List<ExchangeRate> {
        val sourceRates = db.readSourceExchangeRates()
            .takeIf { it.isNotEmpty() }
            ?: getDefaultData()

        validate(sourceRates)
        return sourceRates
    }

    /**
     * Validate set of rates
     * @return true if rates need to be recalculated
     */
    private fun validate(rates: List<ExchangeRate>) {
        if(rates.size != Currency.values().size-1) {
            throw IncorrectMoneyOperationException("Invalid length of an exchange rates list")
        }

        val fromSet = mutableSetOf<Currency>()
        val toSet = mutableSetOf<Currency>()

        rates.forEach { exchangeRate ->
            if(exchangeRate.quoteFactor <= 0.0) {
                throw IncorrectMoneyOperationException("A value of a quote must be positive")
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
    }

    /**
     * Returns exchange rates if source data is empty
     */
    private fun getDefaultData(): List<ExchangeRate> =
        listOf(
            ExchangeRate(Currency.RUB, Currency.USD, 65.0),
            ExchangeRate(Currency.EUR, Currency.USD, 0.87))
}