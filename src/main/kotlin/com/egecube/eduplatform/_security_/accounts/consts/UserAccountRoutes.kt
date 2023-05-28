package com.egecube.eduplatform._security_.accounts.consts

import com.egecube.eduplatform._security_.routes.BaseRoute

object UserAccountRoutes {
    const val ACCOUNTS = "${BaseRoute.BASE_ROUTE}/accounts"
    const val ACCOUNT = "${ACCOUNTS}/{id}"
}