package com.egecube.eduplatform.subjectsManagement.participants.domain

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
open class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @JsonProperty("name")
    open var name: String,
    open var secondName: String,
    open var email: String,

    open val participantRole: String,

    @ManyToMany(mappedBy = "participants")
    open val relatedSubjects: MutableSet<Subject> = mutableSetOf(),

    @ManyToMany
    open val relatedCourses: MutableSet<Course> = mutableSetOf(),

    )