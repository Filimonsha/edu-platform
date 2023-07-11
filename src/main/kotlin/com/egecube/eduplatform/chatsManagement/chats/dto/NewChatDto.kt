package com.egecube.eduplatform.chatsManagement.chats.dto

data class NewChatDto(
    val name: String,
    val participants: List<Long>
)
