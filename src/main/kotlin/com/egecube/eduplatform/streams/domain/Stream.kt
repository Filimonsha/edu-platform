package com.egecube.eduplatform.streams.domain

import com.egecube.eduplatform.schedules.CommonEvent
import jakarta.persistence.*

@Entity
@Table(name = "streams_active")
open class Stream (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long,
    open val webinarId: Long,
    open var externalLink: String? = null,
    @Enumerated(EnumType.STRING)
    open val webinarStatus: StreamStatus = StreamStatus.PLANNED,
//    @OneToOne
//    open var eventDescription: CommonEvent
)