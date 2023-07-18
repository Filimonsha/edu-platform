package com.egecube.eduplatform.chatsManagement.consts

object ChatRoutes {
    const val CHATS_ROUTE = "/api/chats-management"
    const val CHAT_ROUTE = "$CHATS_ROUTE/{chatId}"
    const val MESSAGES_ROUTE = "$CHAT_ROUTE/messages"
}