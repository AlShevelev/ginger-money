package com.syleiman.gingermoney.core.utils.encryption

import javax.crypto.Cipher

interface Encryptor {
    /**
     *
     */
    fun encrypt(data: ByteArray?): ByteArray?

    /**
     *
     */
    fun decrypt(data: ByteArray?): ByteArray?
}