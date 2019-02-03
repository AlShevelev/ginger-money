package com.syleiman.gingermoney.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.syleiman.gingermoney.core.global_entities.date_time.ZonedDateTimeSplit
import com.syleiman.gingermoney.core.global_entities.date_time.toSplit
import com.syleiman.gingermoney.core.storages.db.type_converters.DateTimeTypeConverter
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.ZonedDateTime

/**
 *
 */
@RunWith(AndroidJUnit4::class)
class DateTimeTypeConverterTest {
    /**
     *
     */
    @Test
    fun fromZonedDateTimeSplitToDb_null() {
        // Arrange
        val source: ZonedDateTimeSplit? = null
        val converter = DateTimeTypeConverter()

        // Act
        val result = converter.fromZonedDateTimeSplitToDb(source)

        // Assert
        assertNull(result)
    }

    /**
     *
     */
    @Test
    fun fromDbToZonedDateTimeSplit_null() {
        // Arrange
        val source: ByteArray? = null
        val converter = DateTimeTypeConverter()

        // Act
        val result = converter.fromDbToZonedDateTimeSplit(source)

        // Assert
        assertNull(result)
    }

    /**
     *
     */
    @Test
    fun toDbFromDb() {
        // Arrange
        val sourceValue = ZonedDateTime.now().toSplit()
        val converter = DateTimeTypeConverter()

        // Act
        val restoredValue = converter.fromDbToZonedDateTimeSplit(converter.fromZonedDateTimeSplitToDb(sourceValue))

        // Assert
        assertNotNull(restoredValue)
        assertEquals(sourceValue.seconds, restoredValue!!.seconds)
        assertEquals(sourceValue.nanoseconds, restoredValue.nanoseconds)
        assertEquals(sourceValue.timeZoneId, restoredValue.timeZoneId)
    }
}