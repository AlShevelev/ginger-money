package com.syleiman.gingermoney.core.storages.key_value

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod

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

    /**
     *
     */
    fun setDefaultCurrency(currency: Currency)

    /**
     *
     */
    fun getDefaultCurrency(): Currency?

    /**
     *
     */
    fun setAppProtectionMethod(appProtectionMethod: AppProtectionMethod)

    /**
     *
     */
    fun getAppProtectionMethod(): AppProtectionMethod?
}