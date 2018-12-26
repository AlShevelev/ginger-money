package com.syleiman.gingermoney.core.globalEntities.dateTime

/**
 * [org.threeten.bp.ZonedDateTime] instance as a set of simple values
 */
@Suppress("SpellCheckingInspection")
data class ZonedDateTimeSplit(
    val seconds: Long,
    val nanoseconds: Int,
    val timeZoneId: String
)