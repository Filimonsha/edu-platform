package com.egecube.eduplatform.quizGame.activeGames

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.chats.NewChatDto
import com.egecube.eduplatform.quizGame.activeGames.actions.QuizActions
import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import com.egecube.eduplatform.quizGame.activeGames.dto.QuizActionsDto
import com.egecube.eduplatform.quizGame.activeGames.utils.FieldUtils
import com.egecube.eduplatform.quizGame.activeGames.utils.GameActionsService
import com.egecube.eduplatform.quizGame.consts.QuizGameData
import com.egecube.eduplatform.quizGame.websockets.PlayerNotifications
import com.egecube.eduplatform.tasksManagement.tasks.SimpleTaskService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class GameService(
    private val chatsService: ChatsService,
    private val taskService: SimpleTaskService,
    private val gameRepository: GameRepository,
    private val notifications: PlayerNotifications,
    private val actionsService: GameActionsService,
    private val fieldUtils: FieldUtils
) {

    fun initializeAndSaveGame(roomId: Int): Game {
        val newChat = chatsService.createNewChat(
            NewChatDto("Chat for game $roomId")
        )
        val tasksSet = taskService.getNumberOfSimpleTasks(QuizGameData.Q_ELEMENTS)
        val taskFields = fieldUtils.makeEmptyFieldWithElements(QuizGameData.Q_ELEMENTS, tasksSet.map { it.id.toHexString() })

        val newGame = Game(
            appendedChatId = newChat!!,
            tasksSet = tasksSet,
            gameField = taskFields,
            postedAnswers = ArrayList(),
            pickedForAnswer = hashSetOf(),
            participants = hashSetOf(),
            roomId = roomId
        )
        return gameRepository.save(newGame)
    }

    fun tryStartGame(gameId: ObjectId, userId: Long): Game {
        val game = gameRepository.findById(gameId).get()
        game.startApproved.add(userId)
        if (game.startApproved.size == game.participants.size) {
            game.started = true
        }
        notifications.notifyOfGameState(game)
        return gameRepository.save(game)
    }

    fun addParticipants(game: Game, participant: Long): Game {
        game.participants.add(participant)
        return gameRepository.save(game)
    }

    fun removeParticipant(game: Game, userId: Long): Game {
        game.participants.remove(userId)
        return gameRepository.save(game)
    }

    fun getGameState(gameId: ObjectId): Game {
        return gameRepository.findById(gameId).get()
    }

    fun parseActionAndPerform(action: QuizActionsDto, gameId: ObjectId): Game {
        val result = when (action.actionType) {
            QuizActions.SUBMIT_ANSWER ->
                actionsService.checkAndAddAnswerToGame(gameId, (action as QuizActionsDto.SubmitAnswer).answer!!)
            QuizActions.PICK_FOR_ANSWER ->
                actionsService.pickForAnswer(gameId, (action as QuizActionsDto.PickForAnswer).questionId!!)
            QuizActions.UNPICK_FOR_ANSWER ->
                actionsService.unpickForAnswer(gameId, (action as QuizActionsDto.UnPickForAnswer).questionId!!)
            QuizActions.GIVE_UP ->
                actionsService.giveUpInGame(gameId, (action as QuizActionsDto.GiveUp).userId!!)
            else -> null
        }
        return result!!
    }


}