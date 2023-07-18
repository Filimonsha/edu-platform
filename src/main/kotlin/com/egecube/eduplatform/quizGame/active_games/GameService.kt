package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import com.egecube.eduplatform.quizGame.active_games.domain.Game
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.egecube.eduplatform.quizGame.active_games.utils.FieldUtils
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import com.egecube.eduplatform.tasksManagement.TasksService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GameService(
    private val chatsService: ChatsService,
    private val tasksService: TasksService,
    private val gameRepository: GameRepository,
    private val notifications: PlayerNotifications
) {
    private val fieldUtils = FieldUtils()

    fun initializeAndSaveGame(roomId: Int): Game {
        val newChat = chatsService.createNewChat(
            NewChatDto("Chat for game $roomId")
        )
        val tasksSet = tasksService.getNumberOfSimpleTasks(QuizGameData.Q_ELEMENTS)
        val taskFields = fieldUtils.makeEmptyFieldWithElements(QuizGameData.Q_ELEMENTS, tasksSet.map { it.id })

        val newGame = Game(
            appendedChatId = newChat!!,
            tasksSet = tasksSet,
            gameField = taskFields,
            postedAnswers = ArrayList(),
            participants = hashSetOf()
        )
        return gameRepository.save(newGame)
    }

    fun addParticipants(gameId: ObjectId, participant: Long): Game {
        val game = gameRepository.findById(gameId).orElseThrow()
        game.participants.add(participant)
        return gameRepository.save(game)
    }

    fun tryStartGame(gameId: ObjectId, userId: Long, roomId: Int): Game {
        val game = gameRepository.findById(gameId).get()
        game.startApproved.add(userId)
        if (game.startApproved.size == game.participants.size) {
            game.started = true
            notifications.notifyOfGameState(game)
        }
        return gameRepository.save(game)
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