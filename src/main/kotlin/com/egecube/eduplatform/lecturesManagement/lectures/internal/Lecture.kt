package com.egecube.eduplatform.lecturesManagement.lectures.internal

import com.egecube.eduplatform.lecturesManagement.listeners.internal.Listener
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.time.ZonedDateTime

@Entity
data class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val description:String,
    val startsAt: ZonedDateTime,
    val endsAt: ZonedDateTime,

    @ManyToMany
    val listeners: MutableSet<Listener> = mutableSetOf()

)