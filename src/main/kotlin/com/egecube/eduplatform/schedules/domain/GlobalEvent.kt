package com.egecube.eduplatform.schedules.domain

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "global_events")
open class GlobalEvent {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    lateinit var id: UUID

    var assigned: Long? = null
}