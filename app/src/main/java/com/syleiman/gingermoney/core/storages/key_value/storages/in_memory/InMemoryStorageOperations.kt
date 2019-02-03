package com.syleiman.gingermoney.core.storages.key_value.storages.in_memory

import android.util.Base64
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageCommitOperationsInterface
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageReadOperationsInterface

/** */
class InMemoryStorageOperations(private val storage: MutableMap<String, Any>):
    StorageReadOperationsInterface,
    StorageCommitOperationsInterface {

    //region StorageReadOperationsInterface members
    /** Check is item exists  */
    override fun contains(key: String): Boolean = storage.containsKey(key)

    /** Read boolean value  */
    override fun readBoolean(key: String): Boolean? = storage[key]?.let{ it as Boolean}

    /** Read string value  */
    override fun readString(key: String): String? = storage[key]?.let{ it as String}

    /** Read float value  */
    override fun readFloat(key: String): Float? = storage[key]?.let{ it as Float}

    /** Read int value  */
    override fun readInt(key: String): Int? = storage[key]?.let{ it as Int}

    /** Read long value  */
    override fun readLong(key: String): Long? = storage[key]?.let{ it as Long}

    /** Read long value  */
    override fun readBytes(key: String): ByteArray? = readString(key)?.let {Base64.decode(it, Base64.DEFAULT)}
    //endregion

    //region StorageCommitOperationsInterface members
    /** Complete editing  */
    override fun commit() {
        // do nothing
    }

    /** Put boolean value  */
    override fun putBoolean(key: String, value: Boolean) {
        storage[key] = value
    }

    /** Put string value  */
    override fun putString(key: String, value: String) {
        storage[key] = value
    }

    /** Put float value  */
    override fun putFloat(key: String, value: Float) {
        storage[key] = value
    }

    /** Put int value  */
    override fun putInt(key: String, value: Int) {
        storage[key] = value
    }

    /** Put long value  */
    override fun putLong(key: String, value: Long) {
        storage[key] = value
    }

    /** Put byte[] value  */
    override fun putBytes(key: String, value: ByteArray) {
        putString(key, Base64.encodeToString(value, Base64.DEFAULT))
    }

    /** Remove value by key  */
    override fun remove(key: String) {
        storage.remove(key)
    }

    /** Remove all values  */
    override fun removeAll() = storage.clear()
    //endregion
}