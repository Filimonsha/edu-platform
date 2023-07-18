package com.egecube.eduplatform.quizGame.active_games.dto

import com.egecube.eduplatform.quizGame.active_games.domain.Game

data class GameDto(
    val id: String,
    val roomId: Int,
    val appendedChatId: Long,
    val participants: List<Long>,
    val gameField: ArrayList<ArrayList<Pair<Long?, Boolean?>>>,
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>>,
    val startApproved: HashSet<Long>,
    var started: Boolean
) {
    constructor(obj: Game) : this(
        id = obj._id.toHexString(),
        roomId = obj.roomId,
        appendedChatId = obj.appendedChatId,
        participants = obj.participants.toList(),
        gameField = obj.gameField,
        postedAnswers = obj.postedAnswers,
        startApproved = obj.startApproved,
        started = obj.started
    )
}
