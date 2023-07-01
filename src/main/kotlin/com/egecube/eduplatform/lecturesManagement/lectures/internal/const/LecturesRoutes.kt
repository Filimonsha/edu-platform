package com.egecube.eduplatform.lecturesManagement.lectures.internal.const

import com.egecube.eduplatform.lecturesManagement.routes.BaseRoute

object LecturesRoutes {
    const val LECTURES = "${BaseRoute.BASE_PATH}/lectures"
    const val LECTURE ="$LECTURES/{lectureId}"
}