package com.egecube.eduplatform.quiz_game.events

import com.egecube.eduplatform.quiz_game.rooms.domain.PlayerInRoom

data class GameFinished(
    val players: List<PlayerInRoom>,
    val winners: List<Long>
)
