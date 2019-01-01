package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 */
@Entity(tableName = "expense_category")
data class ExpenseCategoryDb (
    @PrimaryKey
    @ColumnInfo(name = "expense_category_id")
    var id: Long,

    @ColumnInfo(name = "name")
    val name: String
)