package com.egecube.eduplatform.subjectsManagement.participants.utils

import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import com.egecube.eduplatform.subjectsManagement.participants.dto.ParticipantRequestDto
import com.egecube.eduplatform.subjectsManagement.participants.dto.ParticipantResponseDto

fun mapRequestToParticipant(
    participantRequestDto: ParticipantRequestDto
) = Participant(
    null,
    participantRequestDto.name,
    participantRequestDto.secondName,
    participantRequestDto.email,
    participantRequestDto.participantRole,
)

fun mapParticipantToResponse(
    participant: Participant
) = ParticipantResponseDto(
    participant.id,
    participant.name,
    participant.secondName,
    participant.email,
    participant.participantRole,
    participant.relatedSubjects.map { subject -> subject.id }.toSet(),
    participant.relatedCourses.map { flow -> flow.id }.toSet()
)