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

    @DeleteMapping(GamesRoutes.ROOM_ROUTE)
    fun getOutOfRoom(
        @PathVariable roomId: Int,
        @RequestBody userId: Long
    ): Int {
        return roomsService.getOutOfQueue(roomId, userId)
    }

    @GetMapping(GamesRoutes.ROOM_ROUTE)
    fun getNumberOfPlayers(
        @PathVariable roomId: Int
    ): Int {
        return roomsService.countInQueue(roomId)
    }
}