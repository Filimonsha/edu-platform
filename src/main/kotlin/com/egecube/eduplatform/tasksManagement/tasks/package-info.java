@NamedInterface("TasksService")
@ApplicationModule(
        allowedDependencies = "homeworksManagement::TaskModels"
)
package com.egecube.eduplatform.tasksManagement.tasks;

import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.NamedInterface;