package com.egecube.eduplatform.chatsManagement.chats

data class NewChatDto(
    val name: String,
    val participants: List<Long> = emptyList()
)
