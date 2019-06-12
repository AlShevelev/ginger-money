package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ExpensesDao {
    @Query("select exists(select 1 from expense where account_id = :accountId)")
    fun exists(accountId: Long): Boolean
}