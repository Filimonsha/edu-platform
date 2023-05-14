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
    val name: String?,
    val secondName: String?,
    val email: String?,

    val participantRole: String?,

    @ManyToMany
    val relatedSubjects: Set<Subject>?,

    @ManyToMany
    val relatedFlows: Set<Flow>?

)