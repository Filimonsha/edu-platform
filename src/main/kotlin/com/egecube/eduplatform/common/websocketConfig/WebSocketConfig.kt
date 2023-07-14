package com.egecube.eduplatform.common.websocketConfig

import com.egecube.eduplatform.common.websocketConfig.routes.ChatWs
import com.egecube.eduplatform.common.websocketConfig.routes.QuizGameWs
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker(
            ChatWs.CHAT_ENDPOINT,
            QuizGameWs.GAME_ENDPOINT
        )
//        registry.enableSimpleBroker(QuizGameWs.GAME_ENDPOINT)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint(
            ChatWs.CHAT_WS,
            QuizGameWs.GAME_WS
        ).setAllowedOrigins("http://localhost:3000").withSockJS()
//        registry.addEndpoint(QuizGameWs.GAME_WS).withSockJS()
    }
}
