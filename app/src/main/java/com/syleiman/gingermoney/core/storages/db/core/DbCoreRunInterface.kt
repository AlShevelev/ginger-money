package com.syleiman.gingermoney.core.storages.db.core

interface DbCoreRunInterface {
    /** Run some code without transaction */
    fun <T> run(action: (DbCoreDaoInterface) -> T): T

    /** Run some code in transaction */
    fun <T> runInTransaction(action: (DbCoreDaoInterface) -> T): T
}