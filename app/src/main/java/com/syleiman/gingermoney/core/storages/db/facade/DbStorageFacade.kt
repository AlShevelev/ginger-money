package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.core.DbCoreRunInterface
import com.syleiman.gingermoney.core.storages.db.entities.AccountGroupSettingsDb
import com.syleiman.gingermoney.core.storages.db.mapping.map
import com.syleiman.gingermoney.core.storages.db.mapping.mapToDb
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.AccountGroupSettings
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color
import javax.inject.Inject

class DbStorageFacade
@Inject
constructor(
    private val db: DbCoreRunInterface
) : DbStorageFacadeInterface {

    override fun updateSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>) {
        db.runInTransaction { dbCore ->
            dbCore.sourceExchangeRate.deleteAll()
            dbCore.sourceExchangeRate.create(sourceExchangeRates.map { it.mapToDb() })
        }
    }

    override fun readSourceExchangeRates(): List<ExchangeRate> =
        db.run { dbCore ->
            dbCore.sourceExchangeRate.readAll().map { it.map() }
        }

    override fun readAccounts(): List<Account> =
        db.run { dbCore ->
            dbCore.accounts.readAll().map { it.map() }
        }

    override fun createAccount(account: Account) {
        db.run { dbCore ->
            dbCore.accounts.create(account.mapToDb())
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
    override fun readAccount(id: Long): Account? =
        db.run { dbCore ->
            dbCore.accounts.read(id)?.map()
        }

    /**
     * Returns true if an account has expenses
     */
    override fun hasExpenses(accountId: Long): Boolean =
        db.run { dbCore ->
            dbCore.expenses.exists(accountId)
        }

    override fun readAccountGroupSettings(): List<AccountGroupSettings> =
        db.run { dbCore ->
            dbCore.accountGroupSettings.readAll().map { it.map() }
        }

    /**
     * [accountGroup] null in case of Total group
     */
    override fun updateAccountGroupSettings(accountGroup: AccountGroup?, currency: Currency) =
        db.runInTransaction { dbCore ->
            val record = dbCore.accountGroupSettings.readAll().firstOrNull { it.accountGroup == accountGroup }

            if(record == null) {
                dbCore.accountGroupSettings.create(
                    AccountGroupSettingsDb(IdUtil.generateLongId(), accountGroup, currency, null, null))
            } else {
                dbCore.accountGroupSettings.update(record.copy(currency = currency))
            }
        }

    /**
     * [accountGroup] null in case of Total group
     */
    override fun updateAccountGroupSettings(accountGroup: AccountGroup?, foregroundColor: Color, backgroundColor: Color) =
        db.runInTransaction { dbCore ->
            val record = dbCore.accountGroupSettings.readAll().firstOrNull { it.accountGroup == accountGroup }

            if(record == null) {
                dbCore.accountGroupSettings.create(
                    AccountGroupSettingsDb(IdUtil.generateLongId(), accountGroup, null, foregroundColor, backgroundColor))
            } else {
                dbCore.accountGroupSettings.update(record.copy(foregroundColor = foregroundColor, backgroundColor = backgroundColor))
            }
        }
}