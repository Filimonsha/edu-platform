package com.egecube.eduplatform.subjectsManagement.courses.consts

import com.egecube.eduplatform.subjectsManagement.subjects.consts.SubjectsRoutes

object CoursesRoutes {
    const val COURSES = "${SubjectsRoutes.SUBJECT}/courses"
    const val COURSE = "${COURSES}/{courseId}"
}