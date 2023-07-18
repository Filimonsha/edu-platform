package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.egecube.eduplatform.quizGame.active_games.dto.StartRequest
import com.egecube.eduplatform.quizGame.consts.GamesRoutes
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping(GamesRoutes.GAME_ROUTE)
    fun startGame(
        @PathVariable gameId: String,
        @RequestBody startRequest: StartRequest
    ): Game {
        return gameService.tryStartGame(
            ObjectId(gameId), startRequest.userId, startRequest.roomId
        )
    }

    @PostMapping(GamesRoutes.GAME_ANSWERS)
    fun addAnswerToGame(
        @RequestBody newAnswer: GameAnswer,
        @PathVariable gameId: String
    ): Game {
        return gameService.checkAndAddAnswerToGame(ObjectId(gameId), newAnswer)
    }

}