package com.egecube.eduplatform.schedules

import jakarta.persistence.*
import org.springframework.lang.NonNull
import java.time.ZonedDateTime
import kotlin.reflect.full.isSubclassOf

@MappedSuperclass
open class CommonEvent (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,
    @NonNull
    open var eventName: String? = null,
    open val description: String? = null,
    @NonNull
    open val startsAt: ZonedDateTime? = null,
    @NonNull
    open val endsAt: ZonedDateTime? = null,
    @ElementCollection
    open val assignedPersonIds: ArrayList<Long> = ArrayList(),
) {

    inline fun <reified T> initCommonWithDto(data: CommonEventDto): T? {
        if (!T::class.isSubclassOf(CommonEvent::class)) return null
        return CommonEvent(
            eventName = data.eventName,
            description = data.description,
            startsAt = data.startsAt,
            endsAt = data.endsAt,
            assignedPersonIds = data.assignedPersonIds
        ) as T
    }
}