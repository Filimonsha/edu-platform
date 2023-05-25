package com.egecube.eduplatform.subjectsManagement.subjects

import com.egecube.eduplatform.subjectsManagement.courses.Course
import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import jakarta.persistence.*


@Entity
class Subject(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val description: String,

    @OneToMany(mappedBy = "subject")
    val courses: Set<Course>,

    @ManyToMany
    val participants: Set<Participant>,

    )