package com.syleiman.gingermoney.core.storages.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Base class for all Db scheme migration
 */
abstract class MigrationBase(startVersion: Int, endVersion: Int): Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.beginTransaction()
        try {
            processMigration(database)
            database.setTransactionSuccessful()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        finally {
            database.endTransaction()
        }
    }

    protected abstract fun processMigration(database: SupportSQLiteDatabase)
}