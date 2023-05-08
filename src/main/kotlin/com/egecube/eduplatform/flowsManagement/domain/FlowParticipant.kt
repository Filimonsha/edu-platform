package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class FlowParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,
    open val name: String?,
    open val secondName: String?,
    open val email: String?,
    open val participantRole: ParticipantRole?,
)