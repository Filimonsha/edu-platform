package com.egecube.eduplatform.subjectsManagement.events

data class ParticipantRegistered(
    val participantId: Long?,
    val participantName: String,
    val participantSecondName: String,
    val participantEmail: String
)