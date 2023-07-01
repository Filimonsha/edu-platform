package com.egecube.eduplatform.lecturesManagement.listeners.internal

import com.egecube.eduplatform.lecturesManagement.lectures.internal.Lecture
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
open class Listener(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @JsonProperty("name")
    open val name: String,
    open val secondName: String,
    open val email: String,

    @ManyToMany(mappedBy = "listeners")
    open val availableLectures: MutableSet<Lecture> = mutableSetOf()
)
