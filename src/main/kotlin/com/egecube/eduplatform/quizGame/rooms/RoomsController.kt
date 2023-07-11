package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.consts.RoomsRoutes
import org.springframework.web.bind.annotation.*

@RestController
class RoomsController(
    private val roomsService: RoomsService
) {

    @PostMapping(RoomsRoutes.ROOMS_ROUTE)
    fun getIntoQueue(
        @RequestBody newWait: Long
    ): Int {
        return roomsService.standIntoQueue(newWait)
    }

    @DeleteMapping(RoomsRoutes.ROOMS_ROUTE)
    fun getOutOfRoom(
        @RequestBody oldWait: Long
    ): Int {
        return roomsService.getOutOfQueue(oldWait)
    }

    @GetMapping(RoomsRoutes.ROOM_ROUTE)
    fun getNumberOfPlayers(
        @PathVariable roomId: Int
    ): Int {
        return roomsService.countInQueue(roomId)
    }
}