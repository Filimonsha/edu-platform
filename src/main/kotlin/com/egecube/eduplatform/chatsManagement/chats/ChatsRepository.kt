package com.egecube.eduplatform.chatsManagement.chats

import com.egecube.eduplatform.chatsManagement.chats.domain.Chat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatsRepository: CrudRepository<Chat, Long>