package com.egecube.eduplatform.quizGame.websockets.dto

data class RoomPlayers(
    val roomNum: Int,
    val playerNum: Int,
    val playerIds: List<Long>,
    val readyIds: List<Long> = emptyList(),
    val gameId: String = ""
)
