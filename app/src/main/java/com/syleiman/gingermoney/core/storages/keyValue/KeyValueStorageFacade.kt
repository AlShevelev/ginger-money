package com.syleiman.gingermoney.core.storages.keyValue

import com.syleiman.gingermoney.core.storages.keyValue.storages.StorageInterface
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import javax.inject.Inject

/** Helper class for access to App-level private shared preferences  */
class KeyValueStorageFacade
@Inject
constructor(
    private val keyValueStorage: StorageInterface,
    private val stringsConverter: StringsConverterInterface
) : KeyValueStorageFacadeInterface {

    private object Keys {
        /** AES-encryption key (for API < 23) */
        const val CRYPTO_KEY_AES = "crypto_key"
    }

    /** */
    override fun putAESCryptoKey(key: ByteArray) =
        keyValueStorage.update {
            it.putString(Keys.CRYPTO_KEY_AES, stringsConverter.toBase64(key))
        }

    /** */
    override fun getAESCryptoKey(): ByteArray? =
        keyValueStorage.read {
            it.readString(Keys.CRYPTO_KEY_AES)
                ?.let { keyAsString -> stringsConverter.fromBase64(keyAsString) }
        }
}