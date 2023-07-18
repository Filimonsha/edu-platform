package com.egecube.eduplatform.quizGame.websockets

import com.egecube.eduplatform.common.websocketConfig.routes.QuizGameWs
import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.websockets.dto.RoomPlayers
import org.bson.types.ObjectId
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class PlayerNotifications(
    private val simpMessaging: SimpMessagingTemplate
) {
    fun notifyOfUsersInRoom(roomId: Int, players: List<Long>, gameId: ObjectId) {
        simpMessaging.convertAndSend(
            "${QuizGameWs.GAME_ENDPOINT}/rooms/${roomId}",
            RoomPlayers(roomId, players.size, players, emptyList(), gameId.toHexString())
        )
        println("notifying of users")

    }

    fun notifyOfGameState(game: Game) {
        simpMessaging.convertAndSend(
            "${QuizGameWs.GAME_ENDPOINT}/${game._id.toHexString()}",
            game
        )
        println("notifying of game")
    }

}