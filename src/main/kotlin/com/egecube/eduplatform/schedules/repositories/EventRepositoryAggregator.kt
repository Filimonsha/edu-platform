package com.egecube.eduplatform.schedules.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventRepositoryAggregator {
    @Autowired
    lateinit var globalEvents: GlobalEventsRepository

    @Autowired
    lateinit var groupEvents: GroupEventRepository

    @Autowired
    lateinit var personalEvents: UserEventRepository
}