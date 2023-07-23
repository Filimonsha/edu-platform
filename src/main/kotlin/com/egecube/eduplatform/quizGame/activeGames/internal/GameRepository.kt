package com.egecube.eduplatform.quizGame.activeGames.internal

import com.egecube.eduplatform.quizGame.activeGames.internal.domain.Game
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface GameRepository: MongoRepository<Game, ObjectId>