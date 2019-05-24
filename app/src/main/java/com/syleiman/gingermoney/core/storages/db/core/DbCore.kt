package com.syleiman.gingermoney.core.storages.db.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.syleiman.gingermoney.core.storages.db.dao.SourceExchangeRateDao
import com.syleiman.gingermoney.core.storages.db.entities.AccountDb
import com.syleiman.gingermoney.core.storages.db.entities.ExpenseCategoryDb
import com.syleiman.gingermoney.core.storages.db.entities.ExpenseDb
import com.syleiman.gingermoney.core.storages.db.entities.SourceExchangeRateDb
import com.syleiman.gingermoney.core.storages.db.type_converters.DateTimeTypeConverter
import com.syleiman.gingermoney.core.storages.db.type_converters.EnumTypeConverter
import com.syleiman.gingermoney.core.storages.db.type_converters.MoneyTypeConverter

@Database(
    entities = [
        AccountDb::class,
        ExpenseCategoryDb::class,
        ExpenseDb::class,
        SourceExchangeRateDb::class
    ],
    version = 1)
@TypeConverters(
    MoneyTypeConverter::class,
    DateTimeTypeConverter::class,
    EnumTypeConverter::class)
abstract class DbCore: RoomDatabase(), DbCoreDaoInterface, DbCoreRunInterface {

    abstract override val sourceExchangeRate: SourceExchangeRateDao

    /**
     * Run some code without transaction
     */
    override fun <T>run(action: (DbCoreDaoInterface) -> T): T = action.invoke(this)

    /**
     * Run some code in transaction
     */
    override fun <T>runInTransaction(action: (DbCoreDaoInterface) -> T): T {
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