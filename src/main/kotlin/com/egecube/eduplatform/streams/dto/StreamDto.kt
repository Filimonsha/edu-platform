package com.egecube.eduplatform.streams.dto

import com.egecube.eduplatform.schedules.dto.EventDto
import com.egecube.eduplatform.streams.domain.StreamStatus

data class StreamDto(
    val common: EventDto,
    var externalLink: String,
    val webinarStatus: StreamStatus = StreamStatus.PLANNED,
)