package com.egecube.eduplatform.schedulesManagement.dto

import java.time.ZonedDateTime

abstract class ScheduleEvent(
    var name: String? = null,
    var description: String? = null,
    var startsAt: ZonedDateTime? = null,
    var endsAt: ZonedDateTime? = null,
)
