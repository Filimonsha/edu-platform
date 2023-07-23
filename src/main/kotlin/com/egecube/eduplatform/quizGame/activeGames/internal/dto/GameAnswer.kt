package com.egecube.eduplatform.quizGame.activeGames.internal.dto

data class GameAnswer(
    val userId: Long,
    val simpleTaskId: String,
    val answer: String
)
