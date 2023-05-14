package com.egecube.eduplatform.classesManagement.domain

import jakarta.persistence.*


@Entity
class Subject(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String?,
    val description: String?,

    @OneToMany(mappedBy = "subject")
    val flows: Set<Flow>?,

    @ManyToMany
    val participants: Set<Participant>?,


    )