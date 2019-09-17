package com.syleiman.gingermoney.core.global_entities.date_time

import android.icu.text.TimeZoneNames
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun ZonedDateTime.toSplit(): ZonedDateTimeSplit {
    val zone = this.zone.id

    val nowInstant = this.toInstant()

    val seconds = nowInstant.epochSecond
    val nanoseconds = nowInstant.nano

    return ZonedDateTimeSplit(seconds, nanoseconds, zone)
}

fun ZonedDateTimeSplit.toZoneDateTime(): ZonedDateTime {
    val restoredInstant = Instant.ofEpochSecond(seconds, nanoseconds.toLong())
    return ZonedDateTime.ofInstant(restoredInstant, ZoneId.of(this.timeZoneId))
}

/**
 * Calculate draft estimate value to fast search intervals of dates
 */
fun ZonedDateTime.getEstimateValue(): Int {
    return this.year * 372 + (this.monthValue - 1) * 31 + this.dayOfMonth
}

/**
 * Returns a copy of this ZonedDateTime with a year, month and day of month values altered.
 * [monthOfYear] value starts from 1 - for January the value is 1
 */
fun ZonedDateTime.withDate(year: Int, monthOfYear: Int, dayOfMonth: Int): ZonedDateTime =
    this.withYear(year).withMonth(monthOfYear).withDayOfMonth(dayOfMonth)

/**
 * Returns a copy of this ZonedDateTime with a hour and minute values altered.
 */
fun ZonedDateTime.withTime(hour: Int, minute: Int): ZonedDateTime = this.withHour(hour).withMinute(minute)

/**
 * Is the day in last two days of week
 */
fun ZonedDateTime.isWeekend(startDayOfWeek: DayOfWeek): Boolean {
    return when(startDayOfWeek) {
        DayOfWeek.MONDAY -> dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
        DayOfWeek.TUESDAY -> dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY
        DayOfWeek.WEDNESDAY -> dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY
        DayOfWeek.THURSDAY -> dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY
        DayOfWeek.FRIDAY -> dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.THURSDAY
        DayOfWeek.SATURDAY -> dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.FRIDAY
        DayOfWeek.SUNDAY -> dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY
    }
}

fun ZonedDateTime.format(format: DateTimeFormat, appResourcesProvider: AppResourcesProvider): String =
    this.format(DateTimeFormatter.ofPattern(appResourcesProvider.getDateTimeFormat(format)))

object ZonedDateTimeUtil {
    fun createZonedDateTime(year: Int, month: Int, day: Int): ZonedDateTime =
        ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.systemDefault())
}