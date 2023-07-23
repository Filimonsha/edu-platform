package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.active_games.dto.GameDto
import com.egecube.eduplatform.quizGame.consts.GamesRoutes
import com.egecube.eduplatform.quizGame.websockets.dto.RoomPlayers
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