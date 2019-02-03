package com.syleiman.gingermoney.encryption

import com.syleiman.gingermoney.core.utils.encryption.Encryptor
import com.syleiman.gingermoney.core.utils.strings_convertation.StringsConverterInterface
import org.junit.Assert
import org.junit.Test

abstract class EncryptorTestBase {
    abstract val converter: StringsConverterInterface
    abstract val encryptionUtils: Encryptor

    /** */
    @Test
    fun encryptDecrypt() {
        // Arrange
        val source = "In a hole in the ground there lived a hobbit."

        // Act
        val testResult = converter.toBytes(source)
            .let { encryptionUtils.encrypt(it) }
            .let { encryptionUtils.decrypt(it) }
            .let { converter.fromBytes(it!!) }

        // Assert
        Assert.assertEquals(source, testResult)
    }

    /** */
    @Test
    fun encrypt_empty() {
        // Arrange
        val source = ByteArray(0)

        // Act
        val testResult = encryptionUtils.encrypt(source)

        // Arrange
        Assert.assertNotNull(testResult)
        Assert.assertEquals(source.size, testResult!!.size)
        Assert.assertEquals(0, testResult.size)
    }

    /** */
    @Test
    fun decrypt_empty() {
        // Arrange
        val source = ByteArray(0)

        // Act
        val testResult = encryptionUtils.decrypt(source)

        // Assert
        Assert.assertNotNull(testResult)
        Assert.assertEquals(source.size, testResult!!.size)
        Assert.assertEquals(0, testResult.size)
    }

    /** */
    @Test
    fun encrypt_null() {
        // Arrange
        val source = null

        // Act
        val testResult = encryptionUtils.encrypt(source)

        // Arrange
        Assert.assertNull(testResult)
    }

    /** */
    @Test
    fun decrypt_null() {
        // Arrange
        val source = null

        // Act
        val testResult = encryptionUtils.decrypt(source)

        // Assert
        Assert.assertNull(testResult)
    }
}