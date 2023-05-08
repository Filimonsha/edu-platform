package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class FlowGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long?,
    open var name: String?,
)