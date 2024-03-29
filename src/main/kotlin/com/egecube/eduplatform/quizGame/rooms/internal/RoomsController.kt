package com.egecube.eduplatform.quizGame.rooms.internal

import com.egecube.eduplatform.quizGame.activeGames.internal.dto.GameDto
import com.egecube.eduplatform.quizGame.consts.GamesRoutes
import com.egecube.eduplatform.quizGame.rooms.RoomsService
import org.springframework.web.bind.annotation.*

@RestController
class RoomsController(
    private val roomsService: RoomsService
) {

    @PostMapping(GamesRoutes.ROOMS_ROUTE)
    fun getIntoQueue(
        @RequestBody newWait: Long
    ): GameDto {
        return GameDto(roomsService.standIntoQueue(newWait))
    }

    @DeleteMapping(GamesRoutes.ROOM_ROUTE)
    fun getOutOfRoom(
        @PathVariable roomId: Int,
        @RequestBody userId: Long
    ): GameDto {
        return GameDto(roomsService.getOutOfQueue(roomId, userId))
    }
}