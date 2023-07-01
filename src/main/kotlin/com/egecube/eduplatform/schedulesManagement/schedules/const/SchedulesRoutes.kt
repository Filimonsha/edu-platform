package com.egecube.eduplatform.schedulesManagement.schedules.const

import com.egecube.eduplatform.schedulesManagement.routes.BaseRoute

object SchedulesRoutes {
    const val SCHEDULES = "${BaseRoute.BASE_PATH}/schedules"
    const val SCHEDULE = "${SCHEDULES}/{scheduleId}"
}