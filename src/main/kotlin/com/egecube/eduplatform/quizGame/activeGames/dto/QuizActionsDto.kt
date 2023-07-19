package com.egecube.eduplatform.quizGame.activeGames.dto

import com.egecube.eduplatform.quizGame.activeGames.actions.QuizActions

sealed class QuizActionsDto(
    val actionType: QuizActions
) {

    class PickForAnswer(
        val row: Int,
        val column: Int
    ): QuizActionsDto(QuizActions.PICK_FOR_ANSWER)

    class SubmitAnswer(
        val answer: GameAnswer
    ): QuizActionsDto(QuizActions.SUBMIT_ANSWER)

    class GiveUp(): QuizActionsDto(QuizActions.GIVE_UP)
}