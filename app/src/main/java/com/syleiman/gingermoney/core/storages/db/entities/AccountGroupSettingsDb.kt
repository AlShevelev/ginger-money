package com.syleiman.gingermoney.core.storages.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color

@Entity(tableName = "account_group_settings")
data class AccountGroupSettingsDb(
    @PrimaryKey
    @ColumnInfo(name = "account_group_settings_id")
    var id: Long,

    /**
     * Null for Total group
     */
    @ColumnInfo(name = "group", typeAffinity = ColumnInfo.INTEGER)
    val accountGroup: AccountGroup?,

    @ColumnInfo(name = "currency")
    val currency: Currency?,

    @ColumnInfo(name = "foreground_color")
    val foregroundColor: Color?,

    @ColumnInfo(name = "background_color")
    val backgroundColor: Color?
)