package com.egecube.eduplatform.lecturesManagement.dto

import com.egecube.eduplatform.lecturesManagement.domain.LectureStatus
import com.egecube.eduplatform.schedules.dto.EventDto

data class LectureDto(
    val common: EventDto,
    var externalLink: String,
    val webinarStatus: LectureStatus = LectureStatus.PLANNED,
)