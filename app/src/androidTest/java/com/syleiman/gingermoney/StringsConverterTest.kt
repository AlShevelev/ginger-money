package com.syleiman.gingermoney

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverter
import com.syleiman.gingermoney.core.utils.stringsConvertation.StringsConverterInterface
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/** */
@RunWith(AndroidJUnit4::class)
class StringsConverterTest {
    companion object {
        private lateinit var converter: StringsConverterInterface

        @BeforeClass
        @JvmStatic
        fun setUp() {
            converter = StringsConverter()
        }
    }

    /** */
    @Test
    fun toBytes() {
        // Arrange
        val source = "In a hole in the ground there lived a hobbit."

        // Act
        val testResult = converter.toBytes(source)
            .let { converter.fromBytes(it) }

        // Assert
        assertEquals(source, testResult)
    }

    @Test
    fun toBytes_empty() {
        // Arrange
        val source = ""

        // Act
        val testResult = converter.toBytes(source)
            .let { converter.fromBytes(it) }

        // Assert
        assertEquals(source, testResult)
    }

    /** */
    @Test
    fun toBase64() {
        // Arrange
        val source = "In a hole in the ground there lived a hobbit."

        // Act
        val testResult = converter.toBytes(source)
            .let { converter.toBase64(it) }
            .let { converter.fromBase64(it) }
            .let { converter.fromBytes(it) }

        // Assert
        assertEquals(source, testResult)
    }

    @Test
    fun toBase64_empty() {
        // Arrange
        val source = ""

        // Act
        val testResult = converter.toBytes(source)
            .let { converter.toBase64(it) }
            .let { converter.fromBase64(it) }
            .let { converter.fromBytes(it) }

        // Assert
        assertEquals(source, testResult)
    }
}