package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.storages.db.core.DbCoreRunInterface
import com.syleiman.gingermoney.core.storages.db.mapping.map
import com.syleiman.gingermoney.core.storages.db.mapping.mapToDb
import javax.inject.Inject

/**
 *
 */
class DbStorageFacade
@Inject
constructor(
    private val db: DbCoreRunInterface
) : DbStorageFacadeInterface {

    /**
     *
     */
    override fun storeSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>) {
        db.runInTransaction { dbCore ->
            dbCore.sourceExchangeRate.clear()
            dbCore.sourceExchangeRate.insert(sourceExchangeRates.map { it.mapToDb() })
        }
    }

    /**
     *
     */
    override fun getSourceExchangeRates(): List<ExchangeRate> =
        db.run { dbCore ->
            dbCore.sourceExchangeRate.getAll().map { it.map() }
        }
}