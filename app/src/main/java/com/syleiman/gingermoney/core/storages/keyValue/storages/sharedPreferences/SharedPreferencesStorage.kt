package com.syleiman.gingermoney.core.storages.keyValue.storages.sharedPreferences

import android.content.Context
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageBase
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageCommitOperationsInterface
import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageReadOperationsInterface
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
    override fun createReadOperationsInstance(): StorageReadOperationsInterface =
        SharedPreferencesStorageReadOperations(context, storageName)

    /** Create proxy for read */
    override fun createWriteOperationsInstance(): StorageCommitOperationsInterface =
        SharedPreferencesStorageUpdateOperations(context, storageName)
}