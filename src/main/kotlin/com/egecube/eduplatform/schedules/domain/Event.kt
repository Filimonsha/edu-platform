package com.egecube.eduplatform.schedules.domain

import com.egecube.eduplatform.schedules.dto.EventDto
import jakarta.persistence.*
import org.springframework.lang.NonNull
import java.time.ZonedDateTime
import kotlin.reflect.full.isSubclassOf

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class Event (
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    open val id: Long? = null,
    @NonNull
    open var eventName: String? = null,
    open var description: String? = null,
    @NonNull
    open var startsAt: ZonedDateTime? = null,
    @NonNull
    open var endsAt: ZonedDateTime? = null,
    @ElementCollection(fetch = FetchType.LAZY)
    open var assignedPersonIds: List<Long> = emptyList(),
) {

    inline fun <reified T> initCommonWithDto(data: EventDto): T? {
        if (!T::class.isSubclassOf(Event::class)) return null
        val inst = T::class.java.getConstructor().newInstance() as Event
        inst.eventName = data.eventName
        inst.description = data.description
        inst.startsAt = data.startsAt
        inst.endsAt = data.endsAt
        inst.assignedPersonIds = data.assignedPersonIds
        return inst as T
    }
}