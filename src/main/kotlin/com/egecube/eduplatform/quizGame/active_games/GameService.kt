package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.tasksManagement.Task
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.egecube.eduplatform.quizGame.active_games.utils.FieldUtils
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.tasksManagement.TasksService
import org.springframework.stereotype.Service

@Service
class GameService(
    private val chatsService: ChatsService,
    private val tasksService: TasksService,
    private val gameRepository: GameRepository
) {
    private val fieldUtils = FieldUtils()

    fun startGame(players: List<PlayerInRoom>) {
        val newChat = chatsService.createNewChat(
            NewChatDto(
                "Chat for game ${players.first().roomId}",
                players.map { it.userId }
            )
        )
        val tasksSet = tasksService.getNumberOfSimpleTasks(QuizGameData.Q_ELEMENTS)
        val taskFields = fieldUtils.makeEmptyFieldWithElements(QuizGameData.Q_ELEMENTS, tasksSet.map { it.id })

        val newGame = Game(newChat!!, tasksSet, taskFields, ArrayList())
        gameRepository.save(newGame)

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