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

    @Column(nullable = false, length = 100)
    open var name: String? = null

    //TODO(Check user id class here)
    @Column(length = 100)
    open var assignedTo: Long? = null

    @Embedded
    open var eventDescription: EventDescription? = null

    open var timeStamp: LocalDateTime? = null
}