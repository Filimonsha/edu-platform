package com.egecube.eduplatform.subjectsManagement.courses

import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.egecube.eduplatform.subjectsManagement.subjects.Subject
import jakarta.persistence.*

@Entity
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,


    @ManyToOne(fetch = FetchType.LAZY)
    val subject: Subject,

    @ManyToMany
    val participants: MutableSet<Participant>,


)