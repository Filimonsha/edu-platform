package com.egecube.eduplatform.schedules.domain.events

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
open class EventDescription {
    @Column(nullable = false, length = 100)
    open var name: String? = null

    open var description: String? = null
    open var eventType = EventType.NONE
    open var timeStamp: LocalDateTime? = null
    open var appendedLink: LocalDateTime? = null
}