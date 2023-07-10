package com.egecube.eduplatform.quizGame.rooms

import com.egecube.eduplatform.quizGame.active_games.GamesService
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import org.springframework.stereotype.Service

@Service
class RoomsService(
    private val gamesService: GamesService,
    private val playerNotifications: PlayerNotifications
) {
    private val roomSize = 2

    private var roomSequence: Int = 10
    private val playersAwaiting = ArrayDeque<PlayerInRoom>()

    fun standIntoQueue(userId: Long): Int {
        return if (playersAwaiting.isEmpty()) {
            roomSequence += 1
            addToQueueAndNotify(userId, roomSequence)
            roomSequence
        } else {
            val lastRoom = playersAwaiting.last().roomId
            addToQueueAndNotify(userId, lastRoom)
            if (playersAwaiting.size == roomSize) {
                gamesService.startGame(playersAwaiting)
                playersAwaiting.clear()
            }
            lastRoom
        }
    }

    fun getOutOfQueue(userId: Long): Int {
        var room = 0
        val player = playersAwaiting.find { it.userId == userId }
        if (player != null) {
            room = player.roomId
            playersAwaiting.remove(player)
            playerNotifications.notifyOfUsersInRoom(room)
        }
        return room
    }

    fun countInQueue(roomId: Int) = playersAwaiting.filter { it.roomId == roomId }.size

    private fun addToQueueAndNotify(userId: Long, roomId: Int) {
        playersAwaiting.addLast(PlayerInRoom(userId, roomId))
        playerNotifications.notifyOfUsersInRoom(roomId)
    }

}