package com.syleiman.gingermoney.core.storages.db.facade

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.dto.entities.Account

interface DbStorageFacadeInterface {
    fun updateSourceExchangeRates(sourceExchangeRates: List<ExchangeRate>)

    fun getSourceExchangeRates(): List<ExchangeRate>

    fun getAccounts(): List<Account>

    fun addAccount(account: Account)
}