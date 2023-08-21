package com.egecube.eduplatform._security_.tokens.consts

import com.egecube.eduplatform._security_.routes.BaseRoute

object TokensRoutes {
    const val ACC_TOKENS = "${BaseRoute.BASE_ROUTE}/tokens/access"
    const val REF_TOKENS = "${BaseRoute.BASE_ROUTE}/tokens/refresh"
}