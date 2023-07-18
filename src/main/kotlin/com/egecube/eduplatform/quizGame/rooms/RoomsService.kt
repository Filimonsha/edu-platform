package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.active_games.GameService
import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import com.egecube.eduplatform.quizGame.websockets.dto.RoomPlayers
import org.springframework.stereotype.Service

@Service
class RoomsService(
    private val gameService: GameService,
    private val playerNotifications: PlayerNotifications
) {
    private val roomSize = QuizGameData.ROOM_SIZE

    private var roomSequence: Int = 10
    private var gameSequence: Game? = null
    private val playersAwaiting = ArrayDeque<PlayerInRoom>()

    fun standIntoQueue(userId: Long): RoomPlayers {
        if (playersAwaiting.size == roomSize) {
            playersAwaiting.clear()
            gameSequence = null
        }
        if (playersAwaiting.isEmpty()) {
            roomSequence += 1
            val newGame = gameService.initializeAndSaveGame(roomSequence)
            gameSequence = newGame
            addToQueueAndNotify(userId, roomSequence)
        } else {
            gameService.addParticipants(gameSequence!!._id, userId)
            addToQueueAndNotify(userId, roomSequence)

        }
        if (playersAwaiting.size == roomSize) {
            playerNotifications.notifyOfGameState(gameSequence!!)
        }
        return RoomPlayers(
            roomSequence,
            playersAwaiting.size,
            playersAwaiting.map { it.userId },
            emptyList(),
            gameSequence!!._id.toHexString()
        )

    }

    fun getOutOfQueue(roomId: Int, userId: Long): RoomPlayers {
        val player = playersAwaiting.find {
            it.userId == userId && it.roomId == roomId
        }
        playersAwaiting.remove(player)
        playerNotifications.notifyOfUsersInRoom(
            roomId, playersAwaiting.map { it.userId }, gameSequence!!._id
        )
        return RoomPlayers(
            roomId,
            playersAwaiting.size,
            playersAwaiting.map { it.userId },
            emptyList(),
            gameSequence!!._id.toHexString()
        )
    }

    private fun addToQueueAndNotify(userId: Long, roomId: Int) {
        playersAwaiting.addLast(PlayerInRoom(userId, roomId))
        playerNotifications.notifyOfUsersInRoom(
            roomId, playersAwaiting.map { it.userId }, gameSequence!!._id
        )
    }

}