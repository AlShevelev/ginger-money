package com.syleiman.gingermoney.core.storages.keyValue

/**
 *
 */
interface KeyValueStorageFacadeInterface {
    /**
     *
     */
    fun setAESCryptoKey(key: ByteArray)

    /**
     *
     */
    fun getAESCryptoKey(): ByteArray?

    /**
     *
     */
    fun setAppSetupComplete(isSetupComplete: Boolean)

    /**
     *
     */
    fun isAppSetupComplete(): Boolean

    /**
     *
     */
    fun setMasterPassword(masterPassword: ByteArray)

    /**
     *
     */
    fun getMasterPassword(): ByteArray?
}