package com.egecube.eduplatform.quizGame.active_games.domain

import com.egecube.eduplatform.tasksManagement.Task
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Game(
    val appendedChatId: Long,
    val answersCollection: List<Task>,
    val gameField: ArrayList<ArrayList<Pair<Long?, Boolean?>>>,
    val postedAnswers: ArrayList<Pair<GameAnswer, Boolean>>
)