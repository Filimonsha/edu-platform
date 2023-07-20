package com.egecube.eduplatform.chatsManagement.chats.internal

import com.egecube.eduplatform.chatsManagement.chats.ChatsService
import com.egecube.eduplatform.chatsManagement.consts.ChatRoutes
import com.egecube.eduplatform.chatsManagement.chats.internal.domain.Chat
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class ChatsController (
    private val chatsService: ChatsService
) {

    @GetMapping(ChatRoutes.CHATS_ROUTE)
    fun getAvailableChats(
        principal: Principal
    ): ResponseEntity<List<Chat>> {
        val chats = chatsService.getChatsForUser(principal)
        return if (chats.isNotEmpty()) {
            ResponseEntity.ok().body(chats)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping(ChatRoutes.CHATS_ROUTE)
    fun createNewChat(
        @RequestBody chat: NewChatDto
    ): ResponseEntity<Long> {
        val newChat = chatsService.createNewChat(chat)
        return if (newChat != null) {
            ResponseEntity.ok().body(newChat)
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}