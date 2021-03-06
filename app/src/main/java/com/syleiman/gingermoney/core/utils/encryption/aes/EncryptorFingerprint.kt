package com.syleiman.gingermoney.core.utils.encryption.aes

import javax.crypto.Cipher

/**
 * Interface for Fingerprint authentication
 */
interface EncryptorFingerprint {
    /**
     * Get Cipher for ForFingerprintAuthentication
     */
    fun getCipher(): Cipher
}