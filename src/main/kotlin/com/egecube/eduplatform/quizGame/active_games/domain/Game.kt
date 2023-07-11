package com.egecube.eduplatform.quizGame.active_games.domain

import com.egecube.eduplatform.tasksManagement.Task
import com.egecube.eduplatform.quizGame.active_games.dto.GameAnswer
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Game(
    val gameId: Long,
    val appendedChatId: Long,
    val answersCollection: ArrayList<Task>,
    val postedAnswers: ArrayList<HashMap<GameAnswer, Boolean>>
)