package com.egecube.eduplatform.lecturesManagement.lectures.internal

import com.egecube.eduplatform.lecturesManagement.listeners.internal.Listener
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.time.ZonedDateTime

@Entity
open class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,
    open var name: String,
    open var description: String,
    open var startsAt: ZonedDateTime,
    open var endsAt: ZonedDateTime,

    @ManyToMany
    open val listeners: MutableSet<Listener> = mutableSetOf(),

    open var flowSrc: String? = null,
)