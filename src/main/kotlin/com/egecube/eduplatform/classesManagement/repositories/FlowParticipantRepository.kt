package com.egecube.eduplatform.classesManagement.repositories

import com.egecube.eduplatform.classesManagement.domain.FlowParticipant
import org.springframework.data.jpa.repository.JpaRepository

interface FlowParticipantRepository : JpaRepository<FlowParticipant, Long>