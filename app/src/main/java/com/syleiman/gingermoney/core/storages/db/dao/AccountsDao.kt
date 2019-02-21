package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.syleiman.gingermoney.core.storages.db.entities.AccountDb


/**
 * Dao for [com.syleiman.gingermoney.core.storages.db.entities.AccountDb] entity
 */
@Dao
interface AccountsDao {
    @Query("select * from account")
    fun getAll(): List<AccountDb>
}