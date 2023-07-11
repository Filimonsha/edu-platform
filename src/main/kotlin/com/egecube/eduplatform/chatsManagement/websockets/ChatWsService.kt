package com.egecube.eduplatform.chatsManagement.websockets

import com.egecube.eduplatform.chatsManagement.consts.ChatRoutes
import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ChatWsService(
    private val simpMessaging: SimpMessagingTemplate
) {

    fun notifyChatUsers(message: ChatMessage) {
        println("notifying to ${ChatRoutes.CHAT_ENDPOINT}/${message.chat.id}")
        simpMessaging.convertAndSend("${ChatRoutes.CHAT_ENDPOINT}/${message.chat.id}", message)
    }
}