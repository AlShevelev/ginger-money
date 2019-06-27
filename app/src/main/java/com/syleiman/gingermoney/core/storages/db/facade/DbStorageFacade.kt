package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.AccountGroupSettings
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color

interface DbStorageFacade {
    fun updateSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>)

    fun readSourceExchangeRates(): List<ExchangeRate>

    fun readAccounts(): List<Account>

    fun createAccount(account: Account)

    fun updateAccount(account: Account)

    /**
     * Get account by its Db id
     */
    fun readAccount(id: Long): Account?

    /**
     * Returns true if an account has payments
     */
    fun hasPayments(accountId: Long): Boolean

    fun readAccountGroupSettings(): List<AccountGroupSettings>

    /**
     * [accountGroup] null in case of Total group
     */
    fun updateAccountGroupSettings(accountGroup: AccountGroup?, currency: Currency)

    /**
     * [accountGroup] null in case of Total group
     */
    fun updateAccountGroupSettings(accountGroup: AccountGroup?, foregroundColor: Color, backgroundColor: Color)
}