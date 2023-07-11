package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.egecube.eduplatform.quizGame.active_games.utils.FieldUtils
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.rooms.domain.PlayerInRoom
import com.egecube.eduplatform.tasksManagement.TasksService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GameService(
    private val chatsService: ChatsService,
    private val tasksService: TasksService,
    private val gameRepository: GameRepository
) {
    private val fieldUtils = FieldUtils()

    fun startGame(players: List<PlayerInRoom>): ObjectId {
        val newChat = chatsService.createNewChat(
            NewChatDto(
                "Chat for game ${players.first().roomId}",
                players.map { it.userId }
            )
        )
        val tasksSet = tasksService.getNumberOfSimpleTasks(QuizGameData.Q_ELEMENTS)
        val taskFields = fieldUtils.makeEmptyFieldWithElements(QuizGameData.Q_ELEMENTS, tasksSet.map { it.id })

        val newGame = Game(
            appendedChatId = newChat!!,
            tasksSet = tasksSet,
            gameField = taskFields,
            postedAnswers = ArrayList()
        )
        val savedGame = gameRepository.save(newGame)
        return savedGame._id
    }

    fun getGameState(gameId: ObjectId): Game {
        return gameRepository.findById(gameId).get()
    }

    fun checkAndAddAnswerToGame(gameId: ObjectId, answer: GameAnswer): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        var answerIsCorrect = false

        val taskInfo = gameStat.tasksSet.find { it.id == answer.simpleTaskId }
        val neededField = Pair(answer.simpleTaskId, false)

        val row = gameStat.gameField.indexOfFirst { it.contains(neededField) }
        val col = gameStat.gameField[row].indexOf(neededField)

        @Suppress("KotlinConstantConditions")
        if (col == -1 || row == -1) throw NoSuchElementException("No task")

        if (taskInfo != null && taskInfo.rightAnswer == answer.answer) {
            answerIsCorrect = true
        }
        gameStat.postedAnswers.add(
            Pair(
                GameAnswer(
                    answer.userId,
                    answer.simpleTaskId,
                    answer.answer
                ),
                answerIsCorrect
            )
        )
        gameStat.gameField[row][col] = Pair(answer.simpleTaskId, answerIsCorrect)

        return gameRepository.save(gameStat)
    }
}