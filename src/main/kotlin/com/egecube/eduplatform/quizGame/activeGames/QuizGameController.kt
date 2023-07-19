package com.egecube.eduplatform.quizGame.activeGames

import com.egecube.eduplatform.quizGame.activeGames.dto.GameDto
import com.egecube.eduplatform.quizGame.activeGames.dto.QuizActionsDto
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
    ): GameDto {
        return GameDto(gameService.getGameState(ObjectId(gameId)))
    }

    @PostMapping(GamesRoutes.GAME_ROUTE)
    fun startGame(
        @PathVariable gameId: String,
        @RequestBody userId: Long
    ): GameDto {
        return GameDto(gameService.tryStartGame(ObjectId(gameId), userId))
    }

    @PostMapping(GamesRoutes.GAME_ACTIONS)
    fun addAnswerToGame(
        @RequestBody newAnswer: QuizActionsDto,
        @PathVariable gameId: String
    ): GameDto {
        return GameDto(gameService.parseActionAndPerform(newAnswer, ObjectId(gameId)))
    }

}