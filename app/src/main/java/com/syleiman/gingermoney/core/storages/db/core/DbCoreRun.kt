package com.syleiman.gingermoney.core.storages.db.core

interface DbCoreRun {
    /** Run some code without transaction */
    fun <T> run(action: (DbCoreDao) -> T): T

    /** Run some code in transaction */
    fun <T> runInTransaction(action: (DbCoreDao) -> T): T
}