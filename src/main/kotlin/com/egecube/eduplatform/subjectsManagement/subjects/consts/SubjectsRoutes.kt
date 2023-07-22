package com.egecube.eduplatform.subjectsManagement.subjects.consts

import com.egecube.eduplatform.subjectsManagement.routes.BaseRoute

object SubjectsRoutes {
    const val SUBJECTS = "${BaseRoute.BASE_PATH}/subjects"
    const val SUBJECT = "${SUBJECTS}/{subjectId}"

    const val SUBJECT_PARTICIPANTS = "${SUBJECT}/participants"

}