package com.kanneki.webview.presentation.ui.web

import androidx.lifecycle.ViewModel

class WebViewViewModel: ViewModel() {
    private var clearHistory = false // 清除歷史紀錄用

    fun setClearHistory(type: Boolean) {
        clearHistory = type
    }

    fun getClearHistory() = clearHistory
}