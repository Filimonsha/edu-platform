package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.consts.GamesRoutes
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizGameController(
    private val gameService: GameService
) {

    @GetMapping(GamesRoutes.GAME_ROUTE)
    fun getGameState(
        @PathVariable gameId: String
    ): Game {
        return gameService.getGameState(ObjectId(gameId))
    }

}