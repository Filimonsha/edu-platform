package com.egecube.eduplatform.subjectsManagement.participants

import com.egecube.eduplatform.subjectsManagement.participants.domain.Participant
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantRepository : JpaRepository<Participant, Long>