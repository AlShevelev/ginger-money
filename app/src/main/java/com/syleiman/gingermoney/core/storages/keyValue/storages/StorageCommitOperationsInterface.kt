package com.syleiman.gingermoney.core.storages.keyValue.storages

/** Interface for commit operations of a storage */
interface StorageCommitOperationsInterface: StorageWriteOperationsInterface {
    /** Complete editing  */
    fun commit()
}