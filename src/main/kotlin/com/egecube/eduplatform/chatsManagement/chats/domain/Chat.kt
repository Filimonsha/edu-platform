package com.egecube.eduplatform.chatsManagement.chats.domain

import com.egecube.eduplatform.chatsManagement.messages.domain.ChatMessage
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.lang.NonNull

@Entity
open class Chat (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long,
    @NonNull
    open var name: String,
    @ElementCollection
    open var participants: List<Long> = emptyList(),
    @OneToMany
    @JsonIgnore
    open var messages: MutableSet<ChatMessage> = mutableSetOf(),
) {
    companion object {
        fun build(): Chat = Chat::class.java.getConstructor().newInstance()
    }
}
