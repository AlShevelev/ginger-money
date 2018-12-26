package com.syleiman.gingermoney

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.threetenabp.AndroidThreeTen
import com.syleiman.gingermoney.core.globalEntities.dateTime.toSplit
import com.syleiman.gingermoney.core.globalEntities.dateTime.toZoneDateTime
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.ZonedDateTime

@RunWith(AndroidJUnit4::class)
class ZonedDateTimeSplitTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            AndroidThreeTen.init(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
        }
    }

    /**
     *
     */
    @Test
    fun toSplitFromSplit() {
        // Arrange
        val now = ZonedDateTime.now()

        // Act
        val split = now.toSplit()
        val nowRestored = split.toZoneDateTime()

        // Assert
        Assert.assertEquals(now, nowRestored)
    }
}