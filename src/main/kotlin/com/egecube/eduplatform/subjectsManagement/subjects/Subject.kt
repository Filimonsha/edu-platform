package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*


@Entity
open class Subject(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @JsonProperty("name")
    open val name: String,
    open val description: String,

    @OneToMany(mappedBy = "subject")
    open val courses: MutableSet<Course> = mutableSetOf(),

    @ManyToMany
    open val participants: MutableSet<Participant> = mutableSetOf(),

    )