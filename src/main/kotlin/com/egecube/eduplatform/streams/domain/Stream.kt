package com.egecube.eduplatform.streams.domain

import com.egecube.eduplatform.schedules.domain.Event
import jakarta.persistence.*

@Entity
@Table(name = "streams_active")
open class Stream (
    open var externalLink: String,
    @Enumerated(EnumType.STRING)
    open var webinarStatus: StreamStatus = StreamStatus.PLANNED,
): Event() {

    companion object {
        fun build(): Stream = Stream::class.java.getConstructor().newInstance()
    }
}