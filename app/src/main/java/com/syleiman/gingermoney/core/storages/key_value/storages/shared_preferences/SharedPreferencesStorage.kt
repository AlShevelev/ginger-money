package com.syleiman.gingermoney.core.storages.key_value.storages.shared_preferences

import android.content.Context
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageBase
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageCommitOperations
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageReadOperations
import javax.inject.Inject

/** Storage based on shared preferences */
class SharedPreferencesStorage
@Inject
constructor(
    private val context: Context
) : StorageBase() {

    private val storageName
        get() = "${context.packageName}.App"

    /** Create proxy for read */
    override fun createReadOperationsInstance(): StorageReadOperations =
        SharedPreferencesStorageReadOperations(context, storageName)

    /** Create proxy for read */
    override fun createWriteOperationsInstance(): StorageCommitOperations =
        SharedPreferencesStorageUpdateOperations(context, storageName)
}