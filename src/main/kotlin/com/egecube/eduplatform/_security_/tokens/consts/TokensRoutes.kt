package com.egecube.eduplatform._security_.tokens.consts

import com.egecube.eduplatform._security_.routes.BaseRoute

object TokensRoutes {
    const val TOKENS = "${BaseRoute.BASE_ROUTE}/tokens"
    const val TOKEN = "${TOKENS}/{id}"
}