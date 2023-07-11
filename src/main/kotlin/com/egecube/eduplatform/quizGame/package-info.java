@ApplicationModule(
        allowedDependencies = {
                "chatsManagement::ChatsService",
                "tasksManagement::TasksService"
        }
)
package com.egecube.eduplatform.quizGame;

import org.springframework.modulith.ApplicationModule;