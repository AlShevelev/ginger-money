package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.syleiman.gingermoney.core.global_entities.money.Currency

@Entity(tableName = "source_exchange_rate")
data class SourceExchangeRateDb (
    @PrimaryKey
    @ColumnInfo(name = "source_exchange_rate_id")
    var id: Long,

    @ColumnInfo(name = "from")
    val from: Currency,

    @ColumnInfo(name = "to")
    val to: Currency,

    @ColumnInfo(name = "quote_factor")
    val quoteFactor: Double
)