package com.egecube.eduplatform.quizGame.activeGames

import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface GameRepository: MongoRepository<Game, ObjectId>