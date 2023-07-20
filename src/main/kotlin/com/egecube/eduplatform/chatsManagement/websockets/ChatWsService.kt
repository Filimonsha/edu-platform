package com.egecube.eduplatform.chatsManagement.websockets

import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import com.egecube.eduplatform.common.websocketConfig.routes.ChatWs
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ChatWsService(
    private val simpMessaging: SimpMessagingTemplate
) {

    fun notifyChatUsers(message: ChatMessage) {
        simpMessaging.convertAndSend("${ChatWs.CHAT_ENDPOINT}/${message.chat.id}", message)
    }
}