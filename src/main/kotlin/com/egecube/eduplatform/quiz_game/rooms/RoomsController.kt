package com.egecube.eduplatform.quiz_game.rooms

import com.egecube.eduplatform.quiz_game.rooms.dto.PlaceInQueueRequest
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