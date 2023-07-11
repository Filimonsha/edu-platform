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
    @JsonIgnore
    val _id: ObjectId = ObjectId(),
    val appendedChatId: Long = 0,
    @JsonIgnore
    val tasksSet: List<SimpleTask> = emptyList(),
    val gameField: ArrayList<ArrayList<Pair<Long?, Boolean?>>> = ArrayList(),
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>> = ArrayList(),
)