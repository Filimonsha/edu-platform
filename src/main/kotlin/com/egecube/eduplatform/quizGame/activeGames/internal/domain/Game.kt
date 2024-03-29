package com.egecube.eduplatform.quizGame.activeGames.internal.domain

import com.egecube.eduplatform.quizGame.activeGames.internal.dto.GameAnswer
import com.egecube.eduplatform.tasksManagement.tasks.SimpleTask
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Game(
    @Id
    val _id: ObjectId = ObjectId(),
    val roomId: Int,
    val appendedChatId: Long,
    val participants: HashSet<Long>,
    val tasksSet: List<SimpleTask>,
    val gameField: ArrayList<ArrayList<Pair<String?, Boolean?>>>,
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>>,
    val pickedForAnswer: HashSet<String>,
    val startApproved: HashSet<Long> = HashSet(),
    var started: Boolean = false,
    var winner: Long = -1
)