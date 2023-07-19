package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.activeGames.GameService
import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
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

    fun standIntoQueue(userId: Long): Game {
        if (playersAwaiting.size == roomSize) {
            playersAwaiting.clear()
            gameSequence = null
        }
        if (playersAwaiting.isEmpty()) {
            roomSequence += 1
            gameSequence = gameService.initializeAndSaveGame(roomSequence)
        }
        gameService.addParticipants(gameSequence!!, userId)
        addToQueueAndNotify(userId, roomSequence)
        return gameSequence!!
    }

    fun getOutOfQueue(roomId: Int, userId: Long): Game {
        val player = playersAwaiting.find {
            it.userId == userId && it.roomId == roomId
        }
        playersAwaiting.remove(player)
        gameService.removeParticipant(gameSequence!!, userId)
        playerNotifications.notifyOfUsersInRoom(gameSequence!!)
        return gameSequence!!
    }

    private fun addToQueueAndNotify(userId: Long, roomId: Int) {
        gameService.addParticipants(gameSequence!!, userId)
        playersAwaiting.addLast(PlayerInRoom(userId, roomId))
        playerNotifications.notifyOfUsersInRoom(gameSequence!!)
        playerNotifications.notifyOfGameState(gameSequence!!)
    }

}