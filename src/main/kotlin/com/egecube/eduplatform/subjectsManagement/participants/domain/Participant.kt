package com.egecube.eduplatform.subjectsManagement.participants.domain

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.UUID

@Entity
class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @JsonProperty("name")
    var name: String,
    var secondName: String,
    var email: String,

    val participantRole: String,

    @ManyToMany
    val relatedSubjects: Set<Subject> = setOf(),

    @ManyToMany
    val relatedCourses: Set<Course> = setOf()

)