package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.quizGame.Task
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import org.springframework.stereotype.Service

@Service
class GameService {

    fun startGame(players: List<PlayerInRoom>) {
        // create new game
        // get tasks from service
        // add players
        // init empty answers
        // init chat from other module
        // get game id

        // finish, transactions returns game id
        // else on rest
    }

    fun checkAndAddAnswerToGame(gameId: Int, answer: GameAnswer) {
        // get orig answer from game
        // check if answer correct
        // add to game history
        // notify players
    }

    private fun getSimpleTasksCollection(count: Int): ArrayList<Task> {
        // get from other module
        return arrayListOf(Task(), Task(), Task())
    }
}