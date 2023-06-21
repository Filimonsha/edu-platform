package com.egecube.eduplatform.schedules

import org.springframework.data.repository.CrudRepository

interface EventsRepository: CrudRepository<CommonEvent, Long>

// theoretically should pull all the different event tables by their base superclass