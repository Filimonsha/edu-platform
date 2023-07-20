package com.egecube.eduplatform.achievmentsManagement.game_achievements

import com.egecube.eduplatform.achievmentsManagement.types.GameType
import com.egecube.eduplatform.achievmentsManagement.game_achievements.domain.GameAchievements
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GameStatsCounterService(
    private val repository: GameAchievementsRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)


    fun updateGameWon(gameType: GameType, userId: Long) {
        val ach = getOrCreateAchievementList(userId)
        when (gameType) {
            GameType.QUIZ_GAME -> ach.quizGameWon += 1
            else -> {}
        }
        repository.save(ach)
    }

    fun updateGameLost(gameType: GameType, userId: Long) {
        val ach = getOrCreateAchievementList(userId)
        when (gameType) {
            GameType.QUIZ_GAME -> ach.quizGameLost += 1
            else -> {}
        }
        GameType.valueOf("QUIZ_GAME")
    }

    private fun getOrCreateAchievementList(userId: Long): GameAchievements {
        return try {
            repository.findByUserId(userId).get()
        } catch (e: NoSuchElementException) {
            logger.info("Creating new achievement list for $userId")
            val newAch = GameAchievements.build()
            repository.save(newAch)
        }
    }
}