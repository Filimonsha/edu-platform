package com.egecube.eduplatform.achievmentsManagement.game_achievements

import com.egecube.eduplatform.achievmentsManagement.game_achievements.domain.GameAchievements
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface GameAchievementsRepository: CrudRepository<GameAchievements, Long> {
    fun findByUserId(userId: Long): Optional<GameAchievements>
}