package com.syleiman.gingermoney.core.utils.encryption

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