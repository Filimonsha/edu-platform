package com.egecube.eduplatform.schedulesManagement.schedules.internal.dto

import com.egecube.eduplatform.lecturesManagement.lectures.LectureResponseDto
import com.egecube.eduplatform.schedulesManagement.schedules.internal.Schedule


/**
 * A DTO for the [Schedule] entity
 */
data class ScheduleResponseDto(
    val id: Long?,
    val lectures: List<LectureResponseDto>
)
