package com.kanneki.webview.utils

import com.kanneki.webview.BuildConfig

object UrlManager {

    const val TYPE_HOME = 0
    const val TYPE_CATEGORY = 1
    const val TYPE_ACCOUNT = 3

    private const val HOME = ""
    private const val CATEGORY = "search?q=android"
    private const val ACCOUNT = "search?q=android+jetpack"

    fun getUrlByType(type: Int): String {
        val urlType = when(type) {
            TYPE_HOME -> HOME
            TYPE_CATEGORY -> CATEGORY
            TYPE_ACCOUNT -> ACCOUNT
            else -> ""
        }

        return BuildConfig.WEB_URL + urlType
    }
}