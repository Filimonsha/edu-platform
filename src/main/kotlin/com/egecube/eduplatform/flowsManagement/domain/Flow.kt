package com.egecube.eduplatform.flowsManagement.domain

import jakarta.persistence.*


@Entity
class Flow(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String?,
    val description: String?,


)