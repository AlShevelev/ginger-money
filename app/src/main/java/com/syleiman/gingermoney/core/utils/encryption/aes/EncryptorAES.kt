package com.syleiman.gingermoney.core.utils.encryption.aes

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import com.syleiman.gingermoney.application.dependencyInjection.scopes.ApplicationScope
import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import java.security.*
import javax.crypto.KeyGenerator
import javax.inject.Inject

/** Encryption/Decryption via AES */
@RequiresApi(Build.VERSION_CODES.M)
@ApplicationScope
class EncryptorAES
@Inject
constructor() : EncryptorAESBase(), Encryptor {

    companion object {
        private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
        private const val KEY_ALIAS = "gingermoney_encryption_key_aes"
    }

    private val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)

    init {
        keyStore.load(null)

        if (!isKeyExists()) {
            createKey()
        }
    }

    /** */
    override fun getKey(): Key {
        val keyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry
        return keyEntry.secretKey
    }

    /** */
    private fun isKeyExists() = keyStore.containsAlias(KEY_ALIAS)

    /** */
    @Throws(InvalidAlgorithmParameterException::class, NoSuchAlgorithmException::class, NoSuchProviderException::class)
    private fun createKey() {
        val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE_PROVIDER
        )

        val keyGenParameterSpec = KeyGenParameterSpec
            .Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(KEY_SIZE)
            .setRandomizedEncryptionRequired(false)            // To use our own IV
            .build()

        generator.init(keyGenParameterSpec)

        generator.generateKey()     // Generates a key and puts it into keystore
    }
}