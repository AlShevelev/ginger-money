package com.syleiman.gingermoney.core.storages.key_value.storages

/** Interface for commit operations of a storage */
interface StorageCommitOperationsInterface: StorageWriteOperationsInterface {
    /** Complete editing  */
    fun commit()
}