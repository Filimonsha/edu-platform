package com.egecube.eduplatform.lecturesManagement.listeners.internal.const

import com.egecube.eduplatform.lecturesManagement.routes.BaseRoute

object ListenersRoutes {
    const val LISTENERS = "${BaseRoute.BASE_PATH}/listeners"
    const val LISTENER = "${LISTENERS}/{listenerId}"
    const val LISTENER_LECTURES = "${LISTENER}/lectures"
}