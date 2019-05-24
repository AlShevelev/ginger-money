package com.syleiman.gingermoney.core.storages.key_value.storages

interface StorageOperationsInstanceInterface {
    /** Create proxy for read */
    fun createReadOperationsInstance(): StorageReadOperationsInterface

    /** Create proxy for read */
    fun createWriteOperationsInstance(): StorageCommitOperationsInterface
}