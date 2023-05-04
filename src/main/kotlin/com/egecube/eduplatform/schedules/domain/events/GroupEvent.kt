package com.egecube.eduplatform.schedules.domain.events

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "group_events")
open class GroupEvent {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: UUID? = null

    @Column(nullable = false, length = 100)
    open var name: String? = null

    //TODO(Replace with List<Group> from user-flow domain)
    @Column(nullable = false)
    open var groups: String? = null

    @Embedded
    open var eventDescription: EventDescription? = null

    open var timeStamp: LocalDateTime? = null
}