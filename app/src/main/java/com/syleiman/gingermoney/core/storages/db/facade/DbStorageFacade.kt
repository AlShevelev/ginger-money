package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.storages.db.core.DbCoreRunInterface
import com.syleiman.gingermoney.core.storages.db.mapping.map
import com.syleiman.gingermoney.core.storages.db.mapping.mapToDb
import com.syleiman.gingermoney.dto.entities.Account
import javax.inject.Inject

class DbStorageFacade
@Inject
constructor(
    private val db: DbCoreRunInterface
) : DbStorageFacadeInterface {

    override fun storeSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>) {
        db.runInTransaction { dbCore ->
            dbCore.sourceExchangeRate.clear()
            dbCore.sourceExchangeRate.insert(sourceExchangeRates.map { it.mapToDb() })
        }
    }

    override fun getSourceExchangeRates(): List<ExchangeRate> =
        db.run { dbCore ->
            dbCore.sourceExchangeRate.getAll().map { it.map() }
        }

    override fun getAccounts(): List<Account> =
        db.run { dbCore ->
            dbCore.accounts.getAll().map { it.map() }
        }
}