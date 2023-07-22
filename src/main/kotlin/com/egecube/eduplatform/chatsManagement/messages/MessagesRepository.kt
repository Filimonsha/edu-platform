package com.egecube.eduplatform.chatsManagement.messages

import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface MessagesRepository: JpaRepository<ChatMessage, Long> {
    fun findAllByChatId(chatId: Long, pageable: Pageable): Page<ChatMessage>
}