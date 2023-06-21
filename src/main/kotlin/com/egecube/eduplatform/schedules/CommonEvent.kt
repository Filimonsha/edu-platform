package com.egecube.eduplatform.schedules

import com.egecube.eduplatform.subjectsManagement.Course
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToMany
import java.time.ZonedDateTime

@MappedSuperclass
open class CommonEvent (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long,
    open val eventName: String,
    open val description: String,
    open val startsAt: ZonedDateTime,
    open val endsAt: ZonedDateTime,
    @OneToMany
    open val assignedCourses: ArrayList<Course>,
)