package com.egecube.eduplatform.streams.dto

import com.egecube.eduplatform.schedules.CommonEventDto
import com.egecube.eduplatform.streams.domain.StreamStatus

data class StreamDto(
    val common: CommonEventDto,
    var externalLink: String,
    val webinarStatus: StreamStatus = StreamStatus.PLANNED,
)