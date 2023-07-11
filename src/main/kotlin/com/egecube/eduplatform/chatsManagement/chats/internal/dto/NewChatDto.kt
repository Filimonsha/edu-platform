package com.egecube.eduplatform.chatsManagement.chats.internal.dto

data class NewChatDto(
    val name: String,
    val participants: List<Long>
)
