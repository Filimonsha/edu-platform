package com.egecube.eduplatform.schedules

import java.time.ZonedDateTime

data class CommonEventDto(
    val eventName: String,
    val description: String?,
    val startsAt: ZonedDateTime,
    val endsAt: ZonedDateTime,
    val assignedPersonIds: ArrayList<Long>,
)
