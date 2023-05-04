package com.egecube.eduplatform.schedules.domain.events

import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
open class EventDescription {
    open var description: String? = null
    open var eventType = EventType.NONE
    open var appendedLink: LocalDateTime? = null
}