package com.egecube.eduplatform.schedules.domain.events

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "global_events")
open class GlobalEvent {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: UUID? = null

    @Column(nullable = false, length = 100)
    open var name: String? = null

    open var description: String? = null
    open var eventType = EventType.NONE
    open var timeStamp: LocalDateTime? = null
    open var appendedLink: LocalDateTime? = null
}