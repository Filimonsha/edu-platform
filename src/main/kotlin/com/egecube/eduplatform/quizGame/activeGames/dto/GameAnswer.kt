package com.egecube.eduplatform.quizGame.activeGames.dto

data class GameAnswer(
    val userId: Long,
    val simpleTaskId: String,
    val answer: String
)
