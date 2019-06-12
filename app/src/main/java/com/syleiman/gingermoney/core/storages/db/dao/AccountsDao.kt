package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.syleiman.gingermoney.core.storages.db.entities.AccountDb

/**
 * Dao for [com.syleiman.gingermoney.core.storages.db.entities.AccountDb] entity
 */
@Dao
interface AccountsDao {
    @Query("select * from account")
    fun getAll(): List<AccountDb>

    @Query("select * from account where account_id = :id")
    fun get(id: Long): AccountDb?

    @Insert
    fun insert(account: AccountDb)

    @Update
    fun update(account: AccountDb)
}