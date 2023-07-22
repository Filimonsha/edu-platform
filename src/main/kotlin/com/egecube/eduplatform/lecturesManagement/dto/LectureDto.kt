package com.egecube.eduplatform.lecturesManagement.dto

import com.egecube.eduplatform.schedules.dto.EventDto
import com.egecube.eduplatform.lecturesManagement.domain.LectureStatus

data class LectureDto(
    val common: EventDto,
    var externalLink: String,
    val webinarStatus: LectureStatus = LectureStatus.PLANNED,
)