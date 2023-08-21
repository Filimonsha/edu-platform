package com.egecube.eduplatform.quizGame.consts

object GamesRoutes {
    const val GAMES_ROUTE = "/api/games/quiz"
    const val GAME_ROUTE = "$GAMES_ROUTE/{gameId}"
    const val GAME_ACTIONS = "$GAME_ROUTE/actions"
    const val ROOMS_ROUTE = "${GAMES_ROUTE}/rooms"
    const val ROOM_ROUTE = "$ROOMS_ROUTE/{roomId}"
}