package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
open class Flow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long?,
    open val name: String?,
    open val description: String?,

    @OneToMany
    val groups: List<FlowGroup>,
)