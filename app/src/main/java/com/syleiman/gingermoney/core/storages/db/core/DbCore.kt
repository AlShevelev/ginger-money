package com.syleiman.gingermoney.core.storages.db.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.syleiman.gingermoney.core.storages.db.dao.SourceExchangeRateDao
import com.syleiman.gingermoney.core.storages.db.entities.*
import com.syleiman.gingermoney.core.storages.db.type_converters.*

@Database(
    entities = [
        AccountDb::class,
        PaymentCategoryDb::class,
        PaymentDb::class,
        SourceExchangeRateDb::class,
        AccountGroupSettingsDb::class
    ],
    version = 1)
@TypeConverters(
    MoneyTypeConverter::class,
    DateTimeTypeConverter::class,
    EnumTypeConverter::class)
abstract class DbCore: RoomDatabase(), DbCoreDao, DbCoreRun {

    abstract override val sourceExchangeRate: SourceExchangeRateDao

    /**
     * Run some code without transaction
     */
    override fun <T>run(action: (DbCoreDao) -> T): T = action.invoke(this)

    /**
     * Run some code in transaction
     */
    override fun <T>runInTransaction(action: (DbCoreDao) -> T): T {
        beginTransaction()

        try {
            val result = action.invoke(this)
            setTransactionSuccessful()
            return result
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
        finally {
            endTransaction()
        }
    }
}