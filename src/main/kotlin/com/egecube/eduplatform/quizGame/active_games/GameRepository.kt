package com.egecube.eduplatform.quizGame.active_games

import com.egecube.eduplatform.quizGame.active_games.domain.Game
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface GameRepository: MongoRepository<Game, ObjectId>