package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.rooms.dto.PlaceInQueueRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomsController {

    @PostMapping
    fun getIntoQueue(
        @RequestParam request: PlaceInQueueRequest
    ) {}
}