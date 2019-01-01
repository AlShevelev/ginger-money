package com.syleiman.gingermoney.core.storages.db.typeConverters

import androidx.room.TypeConverter
import com.syleiman.gingermoney.core.globalEntities.dateTime.ZonedDateTimeSplit
import java.nio.ByteBuffer

/**
 *
 */
class DateTimeTypeConverter {
    /**
     *
     */
    @TypeConverter
    fun fromZonedDateTimeSplitToDb(sourceData: ZonedDateTimeSplit?): ByteArray? {
        if(sourceData == null) {
            return null
        }

        val timeZoneAsBytes = sourceData.timeZoneId.toByteArray(Charsets.UTF_8)

        return ByteBuffer.allocate(12+timeZoneAsBytes.size)
            .putLong(sourceData.seconds)
            .putInt(sourceData.nanoseconds)
            .put(timeZoneAsBytes)
            .array()
    }

    /**
     *
     */
    @TypeConverter
    fun fromDbToZonedDateTimeSplit(sourceData: ByteArray?): ZonedDateTimeSplit? {
        if(sourceData == null) {
            return null
        }

        val buffer = ByteBuffer.wrap(sourceData)

        val seconds = buffer.getLong(0)
        val nanoSeconds = buffer.getInt(8)

        val timeZoneAsBytes = ByteArray(sourceData.size - 12)

        for(i in 12 until sourceData.size) {
            timeZoneAsBytes[i-12] = buffer.get(i)
        }

        return  ZonedDateTimeSplit(seconds, nanoSeconds, timeZoneAsBytes.toString(Charsets.UTF_8))
    }
}