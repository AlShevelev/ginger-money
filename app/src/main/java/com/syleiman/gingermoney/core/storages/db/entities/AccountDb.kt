package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup

/**
 *
 */
@Entity(tableName = "account")
data class AccountDb (
    @PrimaryKey
    @ColumnInfo(name = "account_id")
    var id: Long,

    @ColumnInfo(name = "group", typeAffinity = ColumnInfo.INTEGER)
    val accountGroup: AccountGroup,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "amount", typeAffinity = ColumnInfo.BLOB)
    val amount: Money,

    @ColumnInfo(name = "memo")
    val memo: String?
)