package com.egecube.eduplatform.subjectsManagement.participants.consts

import com.egecube.eduplatform.subjectsManagement.routes.BaseRoute

object ParticipantsRoutes {
    const val PARTICIPANTS = "${BaseRoute.BASE_PATH}/participants"
    const val PARTICIPANT = "${PARTICIPANTS}/participants/{id}"
}