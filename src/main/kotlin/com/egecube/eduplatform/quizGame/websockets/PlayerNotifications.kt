package com.egecube.eduplatform.quizGame.websockets

import com.egecube.eduplatform.common.websocketConfig.routes.QuizGameWs
import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.quizGame.activeGames.dto.GameDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class PlayerNotifications(
    private val simpMessaging: SimpMessagingTemplate
) {
    fun notifyOfUsersInRoom(game: Game) {
        simpMessaging.convertAndSend(
            "${QuizGameWs.GAME_ENDPOINT}/rooms/${game.roomId}",
            game
        )
    }

    fun notifyOfGameState(game: Game) {
        simpMessaging.convertAndSend(
            "${QuizGameWs.GAME_ENDPOINT}/${game._id.toHexString()}",
            GameDto(game)
        )
    }

}