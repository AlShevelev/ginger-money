package com.syleiman.gingermoney.core.storages.db.mapping

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.entities.AccountDb
import com.syleiman.gingermoney.core.storages.db.entities.AccountGroupSettingsDb
import com.syleiman.gingermoney.core.storages.db.entities.SourceExchangeRateDb
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.AccountGroupSettings

//region ExchangeRate
fun SourceExchangeRateDb.map(): ExchangeRate = ExchangeRate(this.from, this.to, this.quoteFactor)

fun ExchangeRate.mapToDb(): SourceExchangeRateDb =
    SourceExchangeRateDb(IdUtil.generateLongId(), this.from, this.to, this.quoteFactor)
//endregion

//region Account
fun AccountDb.map(): Account = Account(this.id, this.accountGroup, this.name, this.amount, this.memo)

fun Account.mapToDb(): AccountDb =
    AccountDb(
        this.id ?: IdUtil.generateLongId(),
        this.accountGroup,
        this.name,
        this.amount,
        this.memo)
//endregion

//region AccountGroupSettings
fun AccountGroupSettingsDb.map() = AccountGroupSettings(
    this.accountGroup,
    this.currency,
    this.foregroundColor,
    this.backgroundColor
)
//endregion