package com.syleiman.gingermoney.core.storages.key_value.storages.combined

import com.syleiman.gingermoney.core.storages.key_value.storages.StorageBase
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageCommitOperations
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageOperationsInstance
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageReadOperations
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.Inject
import javax.inject.Named

/** Storage based on shared preferences and in-memory structure for cashing */
class CombinedStorage
@Inject
constructor(
    @Named("cache") private val cacheStorage: StorageOperationsInstance,
    @Named("persistent") private val persistentStorage: StorageOperationsInstance
): StorageBase() {

    private val lock = ReentrantReadWriteLock()

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperations =
        CombinedStorageReadOperations(
            lock,
            persistentStorage.createReadOperationsInstance(),
            cacheStorage.createReadOperationsInstance(),
            cacheStorage.createWriteOperationsInstance())

    /** Create proxy for read */
    override fun createWriteOperationsInstance(): StorageCommitOperations =
        CombinedStorageUpdateOperations(
            lock,
            persistentStorage.createWriteOperationsInstance(),
            cacheStorage.createWriteOperationsInstance())
}