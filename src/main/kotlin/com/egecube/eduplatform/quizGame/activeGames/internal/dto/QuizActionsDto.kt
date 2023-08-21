package com.egecube.eduplatform.quizGame.activeGames.internal.dto

import com.egecube.eduplatform.quizGame.activeGames.internal.actions.QuizActions
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "actionType"
)
@JsonSubTypes(
    JsonSubTypes.Type(QuizActionsDto.UnPickForAnswer::class, name = QuizActions.UNPICK_FOR_ANSWER),
    JsonSubTypes.Type(QuizActionsDto.PickForAnswer::class, name = QuizActions.PICK_FOR_ANSWER),
    JsonSubTypes.Type(QuizActionsDto.SubmitAnswer::class, name = QuizActions.SUBMIT_ANSWER),
    JsonSubTypes.Type(QuizActionsDto.GiveUp::class, name = QuizActions.GIVE_UP)
)
sealed class QuizActionsDto(
    val actionType: String = ""
) {

    class PickForAnswer(
        val questionId: String? = null
    ) : QuizActionsDto(QuizActions.PICK_FOR_ANSWER)

    class UnPickForAnswer(
        val questionId: String? = null
    ) : QuizActionsDto(QuizActions.UNPICK_FOR_ANSWER)

    class SubmitAnswer(
        val answer: GameAnswer? = null
    ) : QuizActionsDto(QuizActions.SUBMIT_ANSWER)

    class GiveUp(
        val userId: Long? = null
    ) : QuizActionsDto(QuizActions.GIVE_UP)
}