@ApplicationModule(
        allowedDependencies = {
                "lecturesManagement::LecturesService",
                "lecturesManagement::ListenersService",
                "subjectsManagement::SubjectEvents"
        }
)
package com.egecube.eduplatform.schedulesManagement;

import org.springframework.modulith.ApplicationModule;