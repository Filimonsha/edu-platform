package com.egecube.eduplatform.quizGame.activeGames.dto

import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.tasksManagement.dto.SimpleTaskDto

data class GameDto(
    val id: String,
    val roomId: Int,
    val appendedChatId: Long,
    val participants: List<Long>,
    val taskSet: List<SimpleTaskDto>,
    val gameField: ArrayList<ArrayList<Pair<String?, Boolean?>>>,
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>>,
    val startApproved: HashSet<Long>,
    var started: Boolean
) {
    constructor(obj: Game) : this(
        id = obj._id.toHexString(),
        roomId = obj.roomId,
        appendedChatId = obj.appendedChatId,
        participants = obj.participants.toList(),
        taskSet = obj.tasksSet.map { SimpleTaskDto(it) },
        gameField = obj.gameField,
        postedAnswers = obj.postedAnswers,
        startApproved = obj.startApproved,
        started = obj.started
    )
}
