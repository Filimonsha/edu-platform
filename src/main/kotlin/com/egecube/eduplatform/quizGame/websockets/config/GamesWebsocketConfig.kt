package com.egecube.eduplatform.quizGame.websockets.config

import com.egecube.eduplatform.quizGame.consts.QuizGameWsRoutes
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class GamesWebsocketConfig: WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker(QuizGameWsRoutes.GAME_ENDPOINT)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint(QuizGameWsRoutes.GAME_WS)
            .withSockJS()
    }
}