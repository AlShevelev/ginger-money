package com.syleiman.gingermoney.core.utils.encryption.aes

import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import java.security.Key
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

/** Encryption/Decryption via AES for an old API*/
class EncryptorAESOldApi
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    private val encryptor: Encryptor
) : EncryptorAESBase(), Encryptor {

    companion object {
        private const val KEY_SIZE_BYTES = KEY_SIZE / 8         // in bytes
    }

    private var key: Key? = null  // It's a hole in security but RSA alg (used in encryptor) is quite slow...

    init {
        if (!isKeyExists()) {
            createKey()
        }
    }

    override fun getKey(): Key {
        if(key == null) {
            key = keyValueStorage.getAESCryptoKey()!!
                .let { encryptor.decrypt(it) }
                .let { SecretKeySpec(it, "AES") }
        }

        return key!!
    }

    private fun isKeyExists() = keyValueStorage.getAESCryptoKey() != null

    private fun createKey() {
        val generatedKey = ByteArray(KEY_SIZE_BYTES)
        secureRandom.nextBytes(generatedKey)

        val encryptedKey = encryptor.encrypt(generatedKey)
        keyValueStorage.setAESCryptoKey(encryptedKey!!)

        key = SecretKeySpec(generatedKey, "AES")
    }
}