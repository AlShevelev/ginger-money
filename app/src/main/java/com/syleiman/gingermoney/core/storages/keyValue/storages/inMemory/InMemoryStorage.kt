package com.syleiman.gingermoney.core.storages.keyValue.storages.inMemory

import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageBase
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageReadOperationsInterface
import java.util.*
import javax.inject.Inject

/** Storage based on in-memory dictionary */
@ApplicationScope
class InMemoryStorage
@Inject
constructor(): StorageBase() {
    private val storage: MutableMap<String, Any> = TreeMap()

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperationsInterface = InMemoryStorageOperations(storage)

    /** Create proxy for read */
    override fun createWriteOperationsInstance(): StorageCommitOperationsInterface = InMemoryStorageOperations(storage)
}