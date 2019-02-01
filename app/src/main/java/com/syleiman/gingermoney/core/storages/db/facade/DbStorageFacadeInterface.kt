package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.globalEntities.money.ExchangeRate

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