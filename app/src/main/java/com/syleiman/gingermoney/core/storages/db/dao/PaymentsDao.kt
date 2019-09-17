package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.syleiman.gingermoney.core.storages.db.entities.PaymentDb
import com.syleiman.gingermoney.core.storages.db.entities_util.PaymentExtDb

@Dao
interface PaymentsDao {
    @Query("select exists(select 1 from payment where account_id = :accountId)")
    fun exists(accountId: Long): Boolean

    @Insert
    fun create(payment: PaymentDb)

    @Query("select * from payment where create_at_estimate >= :from and create_at_estimate < :to")
    fun read(from: Int, to: Int): List<PaymentExtDb>
}