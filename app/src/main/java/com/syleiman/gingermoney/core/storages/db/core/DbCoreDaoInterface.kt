package com.syleiman.gingermoney.core.storages.db.core

import com.syleiman.gingermoney.core.storages.db.dao.AccountsDao
import com.syleiman.gingermoney.core.storages.db.dao.ExpensesDao
import com.syleiman.gingermoney.core.storages.db.dao.SourceExchangeRateDao

interface DbCoreDaoInterface {
    val sourceExchangeRate: SourceExchangeRateDao

    val accounts: AccountsDao

    val expenses: ExpensesDao
}