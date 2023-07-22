package com.egecube.eduplatform.lecturesManagement.listeners.internal

import java.io.Serializable

/**
 * A DTO for the [Listener] entity
 */
data class ListenerResponseDTO(
    val name: String,
    val secondName: String,
    val email: String,
    val availableLectures: Set<Long?>
) :
    Serializable