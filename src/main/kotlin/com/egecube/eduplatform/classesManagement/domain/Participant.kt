package com.egecube.eduplatform.classesManagement.domain

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @JsonProperty("name")
    var name: String?,
    var secondName: String?,
    var email: String?,

    val participantRole: String?,

    @ManyToMany
    val relatedSubjects: Set<Subject> = setOf(),

    @ManyToMany
    val relatedFlows: Set<Flow> = setOf()

)