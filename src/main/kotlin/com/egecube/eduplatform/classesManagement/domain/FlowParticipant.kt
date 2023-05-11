package com.egecube.eduplatform.classesManagement.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne

@Entity
class FlowParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String?,
    val secondName: String?,
    val email: String?,
    val participantRole: ParticipantRole?,

    @ManyToMany
    val relatedSubjects: Set<Flow>?,

)