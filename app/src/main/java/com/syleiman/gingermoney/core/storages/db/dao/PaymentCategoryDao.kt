package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.syleiman.gingermoney.core.storages.db.entities.PaymentCategoryDb
import com.syleiman.gingermoney.dto.entities.PaymentCategory

@Dao
interface PaymentCategoryDao {
    @Query("select * from payment_category")
    fun readAll(): List<PaymentCategoryDb>

    @Query("select * from payment_category where payment_category_id = :id")
    fun read(id: Long): PaymentCategoryDb?

    @Insert
    fun create(category: PaymentCategoryDb)

    @Update
    fun update(category: PaymentCategoryDb)
}