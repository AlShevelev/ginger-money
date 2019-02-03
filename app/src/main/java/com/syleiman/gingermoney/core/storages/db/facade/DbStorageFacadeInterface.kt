package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate

/**
 *
 */
interface DbStorageFacadeInterface {
    /**
     *
     */
    fun storeSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>)

    /**
     *
     */
    fun getSourceExchangeRates(): List<ExchangeRate>
}