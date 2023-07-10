package com.egecube.eduplatform.lecturesManagement.lectures.internal

import java.io.Serializable
import java.time.ZonedDateTime

/**
 * A DTO for the [Lecture] entity
 */
data class LectureRequestDto(
    val name: String,
    val description: String,
    val startsAt: ZonedDateTime,
    val endsAt: ZonedDateTime,
    val listeners: Set<Long>,
    val flowSrc: String? = null,
) : Serializable