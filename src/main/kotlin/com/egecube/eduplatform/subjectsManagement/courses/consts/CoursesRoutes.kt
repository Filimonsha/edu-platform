package com.egecube.eduplatform.subjectsManagement.courses.consts

import com.egecube.eduplatform.subjectsManagement.routes.BaseRoute

object CoursesRoutes {
    const val COURSES = "${BaseRoute.BASE_PATH}/courses"
    const val COURSE = "${COURSES}/{id}"
}