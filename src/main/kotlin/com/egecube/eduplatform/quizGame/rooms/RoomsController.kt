package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.consts.GamesRoutes
import org.springframework.web.bind.annotation.*

@RestController
class RoomsController(
    private val roomsService: RoomsService
) {

    @PostMapping(GamesRoutes.ROOMS_ROUTE)
    fun getIntoQueue(
        @RequestBody newWait: Long
    ): Int {
        return roomsService.standIntoQueue(newWait)
    }

    @DeleteMapping(GamesRoutes.ROOMS_ROUTE)
    fun getOutOfRoom(
        @RequestBody oldWait: Long
    ): Int {
        return roomsService.getOutOfQueue(oldWait)
    }

    @GetMapping(GamesRoutes.ROOM_ROUTE)
    fun getNumberOfPlayers(
        @PathVariable roomId: Int
    ): Int {
        return roomsService.countInQueue(roomId)
    }
}