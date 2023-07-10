package com.egecube.eduplatform.quiz_game.rooms

import com.egecube.eduplatform.quiz_game.active_games.GamesService
import com.egecube.eduplatform.quiz_game.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.quiz_game.websockets.PlayerNotifications
import org.springframework.stereotype.Service

@Service
class RoomsService(
    private val gamesService: GamesService,
    private val playerNotifications: PlayerNotifications
) {
    private val roomSize = 2

    private var roomSequence: Long = 10
    private val playersAwaiting = ArrayDeque<PlayerInRoom>()

    fun standIntoQueue(userId: Long): Long {
        // If no one is waiting create a room id
        return if (playersAwaiting.isEmpty()) {
            roomSequence += 1
            playersAwaiting.addLast(PlayerInRoom(userId, roomSequence))
            roomSequence
        } else {
            val lastRoom = playersAwaiting.last().roomId
            playersAwaiting.addLast(PlayerInRoom(userId, lastRoom))
            if (playersAwaiting.size == roomSize) {
                gamesService.startGame(playersAwaiting)
            }
            lastRoom
        }
    }

    fun getOutOfQueue(userId: Long) {
        playersAwaiting.filter {
            it.userId != userId
        }
    }

}