package com.egecube.eduplatform.achievmentsManagement.gameAchievements

import com.egecube.eduplatform.quizGame.events.GameFinished
import org.springframework.modulith.ApplicationModuleListener
import org.springframework.stereotype.Component

@Component
class GameAchievementEventListener {
    @ApplicationModuleListener
    fun on(event: GameFinished) {
        println("Detected game finished")
    }
}