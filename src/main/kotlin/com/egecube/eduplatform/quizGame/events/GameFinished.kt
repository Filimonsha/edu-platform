package com.egecube.eduplatform.quizGame.events

import com.egecube.eduplatform.quizGame.rooms.internal.domain.PlayerInRoom

data class GameFinished(
    val players: List<PlayerInRoom>,
    val winners: List<Long>
)
