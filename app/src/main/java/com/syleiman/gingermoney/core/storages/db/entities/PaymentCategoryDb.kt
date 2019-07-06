package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.syleiman.gingermoney.core.global_entities.date_time.ZonedDateTimeSplit

@Entity(tableName = "payment_category")
data class PaymentCategoryDb (
    @PrimaryKey
    @ColumnInfo(name = "payment_category_id")
    var id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lastUsed", typeAffinity = ColumnInfo.BLOB)
    val lastUsed: ZonedDateTimeSplit?
)