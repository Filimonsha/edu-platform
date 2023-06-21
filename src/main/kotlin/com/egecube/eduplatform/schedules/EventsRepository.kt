package com.egecube.eduplatform.schedules

import org.springframework.data.repository.CrudRepository

interface EventsRepository: CrudRepository<CommonEvent, Long>