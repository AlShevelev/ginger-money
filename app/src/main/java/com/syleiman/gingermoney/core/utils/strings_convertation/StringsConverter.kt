package com.syleiman.gingermoney.core.utils.strings_convertation

interface StringsConverter {
    fun toBytes(data: String): ByteArray

    fun toBase64(data: ByteArray): String

    fun fromBase64(data: String): ByteArray

    fun fromBytes(data: ByteArray): String
}