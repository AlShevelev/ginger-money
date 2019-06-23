package com.syleiman.gingermoney.core.storages.key_value.storages

interface StorageOperationsInstance {
    /** Create proxy for read */
    fun createReadOperationsInstance(): StorageReadOperations

    /** Create proxy for read */
    fun createWriteOperationsInstance(): StorageCommitOperations
}