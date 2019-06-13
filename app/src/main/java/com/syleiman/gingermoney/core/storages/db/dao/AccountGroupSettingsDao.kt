package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.syleiman.gingermoney.core.storages.db.entities.AccountGroupSettingsDb

@Dao
interface AccountGroupSettingsDao {
    @Query("select * from account_group_settings")
    fun readAll(): List<AccountGroupSettingsDb>

    @Insert
    fun create(accountSettings: AccountGroupSettingsDb)

    @Update
    fun update(accountSettings: AccountGroupSettingsDb)
}