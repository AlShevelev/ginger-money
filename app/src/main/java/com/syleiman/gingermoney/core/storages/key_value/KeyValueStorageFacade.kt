package com.syleiman.gingermoney.core.storages.key_value

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.storages.StorageInterface
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import org.threeten.bp.DayOfWeek
import javax.inject.Inject

/**
 * Helper class for access to App-level private shared preferences
 */
class KeyValueStorageFacade
@Inject
constructor(
    private val keyValueStorage: StorageInterface,
    private val stringsConverter: StringsConverterInterface
) : KeyValueStorageFacadeInterface {

    private object Keys {
        /**
         * AES-encryption key (for API < 23)
         */
        const val CRYPTO_KEY_AES = "CRYPTO_KEY_AES"

        const val IS_APP_SETUP_COMPLETE = "IS_APP_SETUP_COMPLETE"

        const val MASTER_PASSWORD = "MASTER_PASSWORD"

        const val DEFAULT_CURRENCY = "DEFAULT_CURRENCY"

        const val APP_PROTECTION_METHOD = "APP_PROTECTION_METHOD"

        const val START_DAY_OF_WEEK = "START_DAY_OF_WEEK"
    }

    /**
     *
     */
    override fun setAESCryptoKey(key: ByteArray) =
        keyValueStorage.update {
            it.putString(Keys.CRYPTO_KEY_AES, stringsConverter.toBase64(key))
        }

    /**
     *
     */
    override fun getAESCryptoKey(): ByteArray? =
        keyValueStorage.read {
            it.readString(Keys.CRYPTO_KEY_AES)?.let { keyAsString -> stringsConverter.fromBase64(keyAsString) }
        }

    /**
     *
     */
    override fun setAppSetupComplete(isSetupComplete: Boolean) =
        keyValueStorage.update {
            it.putBoolean(Keys.IS_APP_SETUP_COMPLETE, isSetupComplete)
        }

    /**
     *
     */
    override fun isAppSetupComplete(): Boolean =
        keyValueStorage.read {
            it.readBoolean(Keys.IS_APP_SETUP_COMPLETE) ?: false
        }

    /**
     *
     */
    override fun setMasterPassword(masterPassword: ByteArray) =
        keyValueStorage.update {
            it.putBytes(Keys.MASTER_PASSWORD, masterPassword)
        }

    /**
     *
     */
    override fun getMasterPassword(): ByteArray? =
        keyValueStorage.read {
            it.readBytes(Keys.MASTER_PASSWORD)
        }

    /**
     *
     */
    override fun setDefaultCurrency(currency: Currency) =
        keyValueStorage.update {
            it.putString(Keys.DEFAULT_CURRENCY, currency.toString())
        }

    /**
     *
     */
    override fun getDefaultCurrency(): Currency? =
        keyValueStorage.read {
            it.readString(Keys.DEFAULT_CURRENCY)?.let { currencyStr -> Currency.from(currencyStr) }
        }

    /**
     *
     */
    override fun setAppProtectionMethod(appProtectionMethod: AppProtectionMethod) =
        keyValueStorage.update {
            it.putString(Keys.APP_PROTECTION_METHOD, appProtectionMethod.toString())
        }

    /**
     *
     */
    override fun getAppProtectionMethod(): AppProtectionMethod? =
        keyValueStorage.read {
            it.readString(Keys.APP_PROTECTION_METHOD)?.let { protectionMethodStr -> AppProtectionMethod.from(protectionMethodStr) }
        }

    /**
     *
     */
    override fun setStartDayOfWeek(startDayOfWeek: DayOfWeek) =
        keyValueStorage.update {
            it.putInt(Keys.START_DAY_OF_WEEK, startDayOfWeek.value)
        }

    /**
     *
     */
    override fun getStartDayOfWeek(): DayOfWeek? =
        keyValueStorage.read {
            it.readInt(Keys.START_DAY_OF_WEEK)?.let { dayOfWeekCode -> DayOfWeek.of(dayOfWeekCode) }
        }
}