package com.egecube.eduplatform.chatsManagement.messages

import com.egecube.eduplatform.chatsManagement.consts.ChatRoutes
import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import com.egecube.eduplatform.chatsManagement.messages.dto.NewMessageDto
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController(
    private val messagesService: MessagesService
) {

    @PostMapping(ChatRoutes.CHAT_ROUTE)
    fun postMessageToChat(
        @RequestBody message: NewMessageDto,
        @PathVariable chatId: Long
    ): ResponseEntity<Long> {
        val messageId = messagesService.postMessageToChat(message, chatId)
        return if (messageId != null) {
            ResponseEntity.ok().body(messageId)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping(ChatRoutes.CHAT_ROUTE)
    fun getChatContent(
        @PathVariable chatId: Long,
        @RequestParam page: Int = 0
    ): ResponseEntity<Page<ChatMessage>> {
        return ResponseEntity.ok().body(
            messagesService.getMessagesForChat(chatId, page)
        )
    }
}