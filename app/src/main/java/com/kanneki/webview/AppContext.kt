package com.kanneki.webview

import android.app.Application
import android.content.Context

class AppContext: Application() {

    companion object {
        private var _context: Application? = null

        fun getContext(): Context {
            return _context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        _context = this
    }
}