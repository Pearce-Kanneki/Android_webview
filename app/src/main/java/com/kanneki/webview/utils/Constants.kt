package com.kanneki.webview.utils

import com.kanneki.webview.AppContext

object Constants {

    const val LOGIN_KEY = "login.php"

    fun getString(id: Int) = AppContext.getContext().getString(id)
}