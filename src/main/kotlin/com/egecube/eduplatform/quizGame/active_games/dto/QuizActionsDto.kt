package com.egecube.eduplatform.quizGame.active_games.dto

import com.egecube.eduplatform.quizGame.active_games.actions.QuizActions

sealed class QuizActionsDto(
    val actionType: QuizActions
) {

    class ClickReady(
        val status: Boolean
    ): QuizActionsDto(QuizActions.CLICK_READY)

    class PickForAnswer(
        val row: Int,
        val column: Int
    ): QuizActionsDto(QuizActions.PICK_FOR_ANSWER)

    class SubmitAnswer(
        val answer: GameAnswer
    ): QuizActionsDto(QuizActions.SUBMIT_ANSWER)

    class WriteMessage(
        val content: String
    ): QuizActionsDto(QuizActions.WRITE_MESSAGE)

    class GiveUp(): QuizActionsDto(QuizActions.GIVE_UP)
}