package com.egecube.eduplatform.chatsManagement.messages.domain

import com.egecube.eduplatform.chatsManagement.chats.domain.Chat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Size
import org.springframework.lang.NonNull
import java.time.ZonedDateTime

@Entity
open class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long,
    open var fromId: Long,
    @Column(name = "timeStamp")
    open var timeStamp: ZonedDateTime,
    @NonNull
    @Size(min=1, max=500)
    open var content: String,
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chatId")
    open var chat: Chat
) {
    companion object {
        fun build(): ChatMessage = ChatMessage::class.java.getConstructor().newInstance()
    }
}