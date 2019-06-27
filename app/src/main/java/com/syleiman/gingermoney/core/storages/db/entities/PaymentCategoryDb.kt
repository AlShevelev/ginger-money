package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_category")
data class PaymentCategoryDb (
    @PrimaryKey
    @ColumnInfo(name = "payment_category_id")
    var id: Long,

    @ColumnInfo(name = "name")
    val name: String
)