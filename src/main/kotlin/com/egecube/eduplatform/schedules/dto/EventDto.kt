package com.egecube.eduplatform.schedules.dto

import java.time.ZonedDateTime

data class EventDto(
    val eventName: String,
    val description: String?,
    val startsAt: ZonedDateTime,
    val endsAt: ZonedDateTime,
    val assignedPersonIds: List<Long>,
)
