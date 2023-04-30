package com.egecube.eduplatform.schedules.domain.events

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "user_events")
open class UserEvent {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: UUID? = null

    //TODO(Check user id class here)
    @Column(nullable = false, length = 100)
    open var assignedTo: Long? = null

    @Column(nullable = false, length = 100)
    open var name: String? = null

    open var description: String? = null
    open var eventType = EventType.NONE
    open var timeStamp: LocalDateTime? = null
    open var appendedLink: LocalDateTime? = null
}