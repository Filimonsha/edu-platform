package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*


@Entity
open class Subject(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @JsonProperty("name")
    val name: String,
    val description: String,

    @OneToMany(mappedBy = "subject")
    val courses: MutableSet<Course> = mutableSetOf(),

    @ManyToMany
    val participants: MutableSet<Participant> = mutableSetOf(),

    )