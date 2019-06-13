package com.syleiman.gingermoney.core.storages.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.syleiman.gingermoney.core.storages.db.entities.SourceExchangeRateDb

/**
 * Dao for [com.syleiman.gingermoney.core.storages.db.entities.SourceExchangeRateDb] entity
 */
@Dao
interface SourceExchangeRateDao {
    @Insert
    fun create(sourceExchangeRate: List<SourceExchangeRateDb>)

    @Query("select * from source_exchange_rate")
    fun readAll(): List<SourceExchangeRateDb>

    @Query("delete from source_exchange_rate")
    fun deleteAll()
}