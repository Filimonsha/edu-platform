package com.egecube.eduplatform.chatsManagement.messages

import com.egecube.eduplatform.chatsManagement.chats.ChatsRepository
import com.egecube.eduplatform.chatsManagement.messages.dto.NewMessageDto
import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import com.egecube.eduplatform.chatsManagement.websockets.ChatWsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class MessagesService(
    private val chatsRepository: ChatsRepository,
    private val messagesRepository: MessagesRepository,
    private val chatNotifications: ChatWsService
) {

    private final val pageSize = 10

    fun postMessageToChat(message: NewMessageDto, chatId: Long): Long? {
        return try {
            val chat = chatsRepository.findById(chatId).get()
            val newMessage = ChatMessage.build().also {
                it.chat = chat
                it.content = message.content
                it.fromId = message.fromId
                it.timeStamp = ZonedDateTime.now()
            }
            val saved = messagesRepository.save(newMessage)
            chat.messages.add(newMessage)
            chatsRepository.save(chat)
            chatNotifications.notifyChatUsers(newMessage)
            saved.id
        } catch (e: NoSuchElementException) {
            null
        }

    }

//    fun getMessagesForChat(chatId: Long, page: Int): List<ChatMessage> {
//        return chatsRepository.findById(chatId).get().messages
//            .sortedBy { it.timeStamp }
//            .slice(page * pageSize..(page + 1) * pageSize)
//    }

    fun getMessagesForChat(chatId: Long, page: Int): Page<ChatMessage> {
        return messagesRepository.findAllByChatId(
            chatId,
            PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "timeStamp"))
        )
    }
}