package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
class Flow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    val name: String?,
    val description: String?,

    @OneToMany
    val groups: List<FlowGroup>,
)