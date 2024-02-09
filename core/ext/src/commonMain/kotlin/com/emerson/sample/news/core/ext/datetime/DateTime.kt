package com.emerson.sample.news.core.ext.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

fun Instant?.timesAgo(): String {
    val now: Instant = Clock.System.now()
    val ago = now.minus(this ?: now)

    return when {
        ago.inWholeDays > 1 -> "${ago.inWholeDays} days"
        ago.inWholeDays > 0 -> "${ago.inWholeDays} day"
        ago.inWholeHours > 0 -> "${ago.inWholeHours} hours"
        ago.inWholeMinutes > 0 -> "${ago.inWholeMinutes} minutes"
        else -> "now"
    }
}

fun String?.toDateTime(): Instant? =
    try {
        Instant.parse(this.orEmpty())
    } catch (e: Exception) {
        null
    }