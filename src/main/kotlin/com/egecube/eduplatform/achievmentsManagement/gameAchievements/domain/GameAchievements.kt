package com.egecube.eduplatform.achievmentsManagement.gameAchievements.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class GameAchievements (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long,
    open val userId: Long,
    open var quizGameWon: Long = 0,
    open var quizGameLost: Long = 0
) {
    companion object {
        fun build(): GameAchievements = GameAchievements::class.java.getConstructor().newInstance()
    }
}