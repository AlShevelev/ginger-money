package com.syleiman.gingermoney.core.storages.db.core

import com.syleiman.gingermoney.core.storages.db.dao.*

interface DbCoreDao {
    val sourceExchangeRate: SourceExchangeRateDao

    val accounts: AccountsDao

    val payments: PaymentsDao

    val accountGroupSettings: AccountGroupSettingsDao

    val paymentCategory: PaymentCategoryDao
}