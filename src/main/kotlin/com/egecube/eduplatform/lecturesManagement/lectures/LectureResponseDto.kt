package com.egecube.eduplatform.lecturesManagement.lectures

import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import java.io.Serializable
import java.time.ZonedDateTime
/**
 * A DTO for the [Lecture] entity
 */
data class LectureResponseDto(
    val name: String,
    val description: String,
    val startsAt: ZonedDateTime,
    val endsAt: ZonedDateTime,
) : Serializable