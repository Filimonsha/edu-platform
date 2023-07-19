package com.egecube.eduplatform.quizGame.activeGames

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.quizGame.activeGames.dto.GameAnswer
import com.egecube.eduplatform.quizGame.activeGames.dto.QuizActionsDto
import com.egecube.eduplatform.quizGame.activeGames.utils.FieldUtils
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import com.egecube.eduplatform.tasksManagement.TaskService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GameService(
    private val chatsService: ChatsService,
    private val taskService: TaskService,
    private val gameRepository: GameRepository,
    private val notifications: PlayerNotifications
) {
    private val fieldUtils = FieldUtils()

    fun initializeAndSaveGame(roomId: Int): Game {
        val newChat = chatsService.createNewChat(
            NewChatDto("Chat for game $roomId")
        )
        val tasksSet = taskService.getNumberOfSimpleTasks(QuizGameData.Q_ELEMENTS)
        val taskFields = fieldUtils.makeEmptyFieldWithElements(QuizGameData.Q_ELEMENTS, tasksSet.map { it.id.toHexString() })

        val newGame = Game(
            appendedChatId = newChat!!,
            tasksSet = tasksSet,
            gameField = taskFields,
            postedAnswers = ArrayList(),
            participants = hashSetOf(),
            roomId = roomId
        )
        return gameRepository.save(newGame)
    }

    fun addParticipants(game: Game, participant: Long): Game {
        game.participants.add(participant)
        return gameRepository.save(game)
    }

    fun removeParticipant(game: Game, userId: Long): Game {
        game.participants.remove(userId)
        return gameRepository.save(game)
    }

    fun tryStartGame(gameId: ObjectId, userId: Long): Game {
        val game = gameRepository.findById(gameId).get()
        game.startApproved.add(userId)
        if (game.startApproved.size == game.participants.size) {
            game.started = true
        }
        notifications.notifyOfGameState(game)
        return gameRepository.save(game)
    }

    fun getGameState(gameId: ObjectId): Game {
        return gameRepository.findById(gameId).get()
    }

    fun parseActionAndPerform(action: QuizActionsDto, gameId: ObjectId): Game {
        val answerSubmit = action as QuizActionsDto.SubmitAnswer
        return checkAndAddAnswerToGame(gameId, action.answer)
    }

    fun checkAndAddAnswerToGame(gameId: ObjectId, answer: GameAnswer): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        var answerIsCorrect = false

        val taskInfo = gameStat.tasksSet.find { it.id.toHexString() == answer.simpleTaskId }
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