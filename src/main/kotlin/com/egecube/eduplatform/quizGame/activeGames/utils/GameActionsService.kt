package com.egecube.eduplatform.quizGame.activeGames.utils

import com.egecube.eduplatform.quizGame.activeGames.GameRepository
import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.quizGame.activeGames.dto.GameAnswer
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GameActionsService(
    private val notifications: PlayerNotifications,
    private val gameRepository: GameRepository,
    private val fieldUtils: FieldUtils
) {
    fun pickForAnswer(gameId: ObjectId, questionId: String): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        gameStat.pickedForAnswer.add(questionId)
        notifications.notifyOfGameState(gameStat)
        return gameRepository.save(gameStat)
    }

    fun unpickForAnswer(gameId: ObjectId, questionId: String): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        gameStat.pickedForAnswer.remove(questionId)
        notifications.notifyOfGameState(gameStat)
        return gameRepository.save(gameStat)
    }

    fun giveUpInGame(gameId: ObjectId, userId: Long): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        gameStat.startApproved.remove(userId)
        if (gameStat.startApproved.size == 1) {
            gameStat.winner = gameStat.startApproved.first()
        }
        notifications.notifyOfGameState(gameStat)
        return gameRepository.save(gameStat)
    }

    fun checkAndAddAnswerToGame(gameId: ObjectId, answer: GameAnswer): Game {
        val gameStat = gameRepository.findById(gameId).orElseThrow()
        var answerIsCorrect = false

        val taskInfo = gameStat.tasksSet.find { it._id.toHexString() == answer.simpleTaskId }
        val neededField = Pair(answer.simpleTaskId, false)

        val row = gameStat.gameField.indexOfFirst { it.contains(neededField) }
        if (row == -1) throw NoSuchElementException("Answered")
        val col = gameStat.gameField[row].indexOf(neededField)

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
        notifications.notifyOfGameState(gameStat)

        val answersCount = gameStat.participants.map {
            Pair(it, fieldUtils.countRightAnswersForUser(it, gameStat))
        }
        val trueAnswersCount = answersCount.sumOf { it.second }
        if (trueAnswersCount == gameStat.tasksSet.size) {
            val maxAnswersUser = answersCount.maxBy { it.second }
            gameStat.winner = maxAnswersUser.first
            notifications.notifyOfGameState(gameStat)
        }

        return gameRepository.save(gameStat)
    }
}