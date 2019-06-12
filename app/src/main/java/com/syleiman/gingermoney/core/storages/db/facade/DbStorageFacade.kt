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

    override fun updateSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>) {
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

    override fun addAccount(account: Account) {
        db.run { dbCore ->
            dbCore.accounts.insert(account.mapToDb())
        }
    }

    override fun updateAccount(account: Account) {
        db.run { dbCore ->
            dbCore.accounts.update(account.mapToDb())
        }
    }

    /**
     * Get account by its Db id
     */
    override fun getAccount(id: Long): Account? =
        db.run { dbCore ->
            dbCore.accounts.get(id)?.map()
        }

    /**
     * Returns true if an account has expenses
     */
    override fun hasExpenses(accountId: Long): Boolean =
        db.run { dbCore ->
            dbCore.expenses.exists(accountId)
        }
}