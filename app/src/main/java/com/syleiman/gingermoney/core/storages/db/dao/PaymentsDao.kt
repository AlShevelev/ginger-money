package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.syleiman.gingermoney.core.storages.db.entities.PaymentDb

@Dao
interface PaymentsDao {
    @Query("select exists(select 1 from payment where account_id = :accountId)")
    fun exists(accountId: Long): Boolean

    @Insert
    fun create(payment: PaymentDb)
}