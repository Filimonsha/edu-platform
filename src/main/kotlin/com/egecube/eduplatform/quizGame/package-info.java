@ApplicationModule(
        allowedDependencies = {
                "chatsManagement::ChatsService",
                "tasksManagement::TasksService",
                "common::WsConfig"
        }
)
package com.egecube.eduplatform.quizGame;

import org.springframework.modulith.ApplicationModule;