package com.egecube.eduplatform.lectures.dto

import com.egecube.eduplatform.schedules.dto.EventDto
import com.egecube.eduplatform.lectures.domain.LectureStatus

data class LectureDto(
    val common: EventDto,
    var externalLink: String,
    val webinarStatus: LectureStatus = LectureStatus.PLANNED,
)