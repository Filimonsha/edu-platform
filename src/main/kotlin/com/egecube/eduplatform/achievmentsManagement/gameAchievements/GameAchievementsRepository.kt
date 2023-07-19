package com.egecube.eduplatform.achievmentsManagement.gameAchievements

import com.egecube.eduplatform.achievmentsManagement.gameAchievements.domain.GameAchievements
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface GameAchievementsRepository: CrudRepository<GameAchievements, Long> {
    fun findByUserId(userId: Long): Optional<GameAchievements>
}