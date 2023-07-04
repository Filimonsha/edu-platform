package com.egecube.eduplatform.schedulesManagement.schedules.internal

import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository:JpaRepository<Schedule,Long>