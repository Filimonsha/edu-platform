package com.egecube.eduplatform.flowsManagement.repositories

import com.egecube.eduplatform.flowsManagement.domain.Flow
import org.springframework.data.jpa.repository.JpaRepository

interface FlowRepository : JpaRepository<Flow,Long>