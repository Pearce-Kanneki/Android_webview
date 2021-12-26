package com.kanneki.webview.utils

import android.util.Log
import com.kanneki.webview.BuildConfig

object LogUtils {
    const val TAG = "Kuma"

    fun showDebugLog(message: String, tag:String = TAG){
        if (BuildConfig.DEBUG){
            Log.d(tag, message)
        }
    }

    fun showInfoLog(message: String, tag: String = TAG){
        Log.i(tag,message)
    }

    fun showErrorLog(message: String, tr: Throwable? = null, tag: String = TAG){
        if (BuildConfig.DEBUG){
            Log.e(tag, message, tr)
        }
    }
}