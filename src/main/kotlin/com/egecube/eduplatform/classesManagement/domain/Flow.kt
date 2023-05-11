package com.egecube.eduplatform.classesManagement.domain

import jakarta.persistence.*

@Entity
class Flow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    val subject: Subject?,

    @Column(name = "subject_id")
    val subjectId: Long,
)