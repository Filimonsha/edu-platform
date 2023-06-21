package com.egecube.eduplatform.streams.domain

import com.egecube.eduplatform.schedules.CommonEvent
import jakarta.persistence.*

@Entity
@Table(name = "streams_active")
open class Stream (
    open var externalLink: String? = null,
    @Enumerated(EnumType.STRING)
    open var webinarStatus: StreamStatus = StreamStatus.PLANNED,
): CommonEvent() {

    companion object {
        fun build(): Stream = Stream::class.java.getConstructor().newInstance()
    }
}