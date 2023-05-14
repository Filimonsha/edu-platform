package com.egecube.eduplatform.classesManagement.repositories

import com.egecube.eduplatform.classesManagement.domain.Participant
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantRepository : JpaRepository<Participant, Long>