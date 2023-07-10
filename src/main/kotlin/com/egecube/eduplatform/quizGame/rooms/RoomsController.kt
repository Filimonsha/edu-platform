package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.consts.RoomsRoutes
import com.egecube.eduplatform.quizGame.rooms.dto.PlaceInQueueRequest
import org.springframework.web.bind.annotation.*

@RestController
class RoomsController(
    private val roomsService: RoomsService
) {

    @PostMapping(RoomsRoutes.ROOMS_ROUTE)
    fun getIntoQueue(
        @RequestBody newWait: PlaceInQueueRequest
    ): Int {
        return roomsService.standIntoQueue(newWait.user.toLong())
    }

    @DeleteMapping(RoomsRoutes.ROOMS_ROUTE)
    fun getOutOfRoom(
        @RequestBody oldWait: PlaceInQueueRequest
    ): Int {
        return roomsService.getOutOfQueue(oldWait.user.toLong())
    }

    @GetMapping(RoomsRoutes.ROOM_ROUTE)
    fun getNumberOfPlayers(
        @PathVariable roomId: Int
    ): Int {
        return roomsService.countInQueue(roomId)
    }
}