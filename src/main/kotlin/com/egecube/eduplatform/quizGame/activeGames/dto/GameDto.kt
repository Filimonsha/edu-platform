package com.egecube.eduplatform.quizGame.activeGames.dto

import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.tasksManagement.tasks.SimpleTaskDto

data class GameDto(
    val id: String,
    val roomId: Int,
    val appendedChatId: Long,
    val participants: List<Long>,
    val taskSet: List<SimpleTaskDto>,
    val gameField: List<List<Pair<String?, Boolean?>>>,
    val postedAnswers: List<Pair<GameAnswer, Boolean>>,
    val pickedForAnswer: HashSet<String>,
    val startApproved: HashSet<Long>,
    val started: Boolean,
    val winner: Long
) {
    constructor(obj: Game) : this(
        id = obj._id.toHexString(),
        roomId = obj.roomId,
        appendedChatId = obj.appendedChatId,
        participants = obj.participants.toList(),
        taskSet = obj.tasksSet.map { SimpleTaskDto(it) },
        gameField = obj.gameField,
        postedAnswers = obj.postedAnswers,
        pickedForAnswer = obj.pickedForAnswer,
        startApproved = obj.startApproved,
        started = obj.started,
        winner = obj.winner
    )
}
