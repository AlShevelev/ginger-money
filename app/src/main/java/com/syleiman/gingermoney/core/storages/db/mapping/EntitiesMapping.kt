package com.syleiman.gingermoney.core.storages.db.mapping

import com.syleiman.gingermoney.core.global_entities.date_time.toSplit
import com.syleiman.gingermoney.core.global_entities.date_time.toZoneDateTime
import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.entities.*
import com.syleiman.gingermoney.core.storages.db.entities_util.PaymentExtDb
import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.dto.entities.AccountGroupSettings
import com.syleiman.gingermoney.dto.entities.Payment
import com.syleiman.gingermoney.dto.entities.PaymentCategory

//region ExchangeRate
fun SourceExchangeRateDb.map(): ExchangeRate = ExchangeRate(this.from, this.to, this.quoteFactor)

fun ExchangeRate.map(): SourceExchangeRateDb =
    SourceExchangeRateDb(IdUtil.generateLongId(), this.from, this.to, this.quoteFactor)
//endregion

//region Account
fun AccountDb.map(): Account = Account(
    this.id,
    this.accountGroup,
    this.name,
    this.amount,
    this.memo,
    this.createAt.toZoneDateTime(),
    this.lastUsed?.toZoneDateTime())

fun Account.map(): AccountDb =
    AccountDb(
        this.id ?: IdUtil.generateLongId(),
        this.accountGroup,
        this.name,
        this.amount,
        this.memo,
        this.createAt.toSplit(),
        this.lastUsed?.toSplit())
//endregion

//region AccountGroupSettings
fun AccountGroupSettingsDb.map() = AccountGroupSettings(
    this.accountGroup,
    this.currency,
    this.foregroundColor,
    this.backgroundColor
)
//endregion

fun PaymentCategoryDb.map() = PaymentCategory(
    this.id,
    this.name,
    this.createAt.toZoneDateTime(),
    this.lastUsed?.toZoneDateTime()
)

fun PaymentCategory.map() = PaymentCategoryDb(
    this.id ?: IdUtil.generateLongId(),
    this.name,
    this.createAt.toSplit(),
    this.lastUsed?.toSplit()
)

fun Payment.map() = PaymentDb(
    this.id ?: IdUtil.generateLongId(),
    this.account.id!!,
    this.paymentCategory.id!!,
    this.amount,
    this.memo,
    this.createAt.toSplit(),
    this.createAtEstimate
)

fun PaymentExtDb.map() = Payment(
    this.payment.id,
    this.accounts.first().map(),
    this.paymentCategories.first().map(),
    this.payment.amount,
    this.payment.memo,
    this.payment.createAt.toZoneDateTime(),
    this.payment.createAtEstimate
)