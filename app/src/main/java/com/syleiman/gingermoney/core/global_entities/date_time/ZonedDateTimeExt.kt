package com.syleiman.gingermoney.core.global_entities.date_time

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 *
 */
fun ZonedDateTime.toSplit(): ZonedDateTimeSplit {
    val zone = this.zone.id

    val nowInstant = this.toInstant()

    val seconds = nowInstant.epochSecond
    val nanoseconds = nowInstant.nano

    return ZonedDateTimeSplit(seconds, nanoseconds, zone)
}

/**
 *
 */
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