package com.egecube.eduplatform.chatsManagement.chats

import com.egecube.eduplatform.chatsManagement.chats.internal.domain.Chat
import com.egecube.eduplatform.chatsManagement.chats.internal.dto.NewChatDto
import com.egecube.eduplatform.chatsManagement.chats.internal.ChatsRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class ChatsService(
    private val chatsRepository: ChatsRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getChatsForUser(
        principal: Principal
    ): List<Chat> {
       return chatsRepository.findAll() as ArrayList<Chat>
    }

    fun createNewChat(chatDto: NewChatDto): Long? {
        val newChat = Chat.build().also {
            it.name = chatDto.name
            it.participants = chatDto.participants
            it.messages = mutableSetOf()
        }
        return try {
            val chat = chatsRepository.save(newChat)
            logger.info("Created new chat: ${chat.name}")
            chat.id
        } catch (e: IllegalArgumentException) {
            logger.warn("Unable to create chat ${chatDto.name}")
            null
        }
    }
}