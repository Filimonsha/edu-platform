package com.egecube.eduplatform.subjectsManagement.participants.domain

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
open class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @JsonProperty("name")
    var name: String,
    var secondName: String,
    var email: String,

    val participantRole: String,

    @ManyToMany(mappedBy = "participants")
    val relatedSubjects: MutableSet<Subject> = mutableSetOf(),

    @ManyToMany
    val relatedCourses: MutableSet<Course> = mutableSetOf(),

    )