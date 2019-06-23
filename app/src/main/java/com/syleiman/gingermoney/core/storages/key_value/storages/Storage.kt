package com.syleiman.gingermoney.core.storages.key_value.storages

/** External interface of storage */
interface Storage {
    /** Read data from storage */
    fun <T>read(readFunc: (StorageReadOperations) -> T): T

    /** Update data in storage */
    fun update(updateAction: (StorageWriteOperations) -> Unit)
}