package com.syleiman.gingermoney.core.storages.db.mapping

import com.syleiman.gingermoney.core.global_entities.money.ExchangeRate
import com.syleiman.gingermoney.core.helpers.id.IdUtil
import com.syleiman.gingermoney.core.storages.db.entities.SourceExchangeRateDb

//region ExchangeRate
fun SourceExchangeRateDb.map(): ExchangeRate = ExchangeRate(this.from, this.to, this.quoteFactor)

fun ExchangeRate.mapToDb(): SourceExchangeRateDb =
    SourceExchangeRateDb(IdUtil.generateLongId(), this.from, this.to, this.quoteFactor)
//endregion

