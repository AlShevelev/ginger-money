package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.syleiman.gingermoney.core.storages.db.entities.PaymentCategoryDb

@Dao
interface PaymentCategoryDao {
    @Query("select * from payment_category")
    fun readAll(): List<PaymentCategoryDb>
}