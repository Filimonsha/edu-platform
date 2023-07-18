package com.egecube.eduplatform.quizGame.active_games.domain

import com.egecube.eduplatform.tasksManagement.SimpleTask
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    val tasksSet: List<SimpleTask>,
    val gameField: ArrayList<ArrayList<Pair<Long?, Boolean?>>>,
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>>,
    val startApproved: HashSet<Long> = HashSet(),
    var started: Boolean = false
)