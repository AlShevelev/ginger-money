package com.syleiman.gingermoney.core.storages.keyValue.storages.combined

import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageBase
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageOperationsInstanceInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageReadOperationsInterface
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.Inject
import javax.inject.Named

/** Storage based on shared preferences and in-memory structure for cashing */
class CombinedStorage
@Inject
constructor(
    @Named("cache") private val cacheStorage: StorageOperationsInstanceInterface,
    @Named("persistent") private val persistentStorage: StorageOperationsInstanceInterface
): StorageBase() {

    private val lock = ReentrantReadWriteLock()

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperationsInterface =
        CombinedStorageReadOperations(
            lock,
            persistentStorage.createReadOperationsInstance(),
            cacheStorage.createReadOperationsInstance(),
            cacheStorage.createWriteOperationsInstance())

    /** Create proxy for read */
    override fun createWriteOperationsInstance(): StorageCommitOperationsInterface =
        CombinedStorageUpdateOperations(
            lock,
            persistentStorage.createWriteOperationsInstance(),
            cacheStorage.createWriteOperationsInstance())
}