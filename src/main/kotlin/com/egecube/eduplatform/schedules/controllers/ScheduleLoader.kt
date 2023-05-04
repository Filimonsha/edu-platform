package com.egecube.eduplatform.schedules.controllers

import com.egecube.eduplatform.schedules.repositories.EventRepositoryAggregator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/schedules")
class ScheduleLoader {
    // Варианты - в строке поиска, в куках, в теле запроса

    @Autowired
    private lateinit var eventsRepository: EventRepositoryAggregator

}