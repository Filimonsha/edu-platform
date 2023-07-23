package com.egecube.eduplatform.chatsManagement.chats.internal

import com.egecube.eduplatform.chatsManagement.chats.internal.domain.Chat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatsRepository: CrudRepository<Chat, Long>