package com.syleiman.gingermoney.core.storages.keyValue

/**  */
interface KeyValueStorageFacadeInterface {
    /** */
    fun putAESCryptoKey(key: ByteArray)

    /** */
    fun getAESCryptoKey(): ByteArray?
}