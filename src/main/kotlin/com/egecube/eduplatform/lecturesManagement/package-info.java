@ApplicationModule(
        allowedDependencies = {
                "schedules::ScheduleEvents",
                "subjectsManagement::SubjectEvents"
        }
)
package com.egecube.eduplatform.lecturesManagement;

import org.springframework.modulith.ApplicationModule;