package com.kanneki.webview.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.kanneki.webview.presentation.base.BaseFragment

class MainViewModel:ViewModel() {

//    private val _webViewCanGoBack = MutableStateFlow(false)
//    val webViewCanGoBack = _webViewCanGoBack.asStateFlow()
    private var currentFragment: BaseFragment? = null

    fun setCurrentFragment(fragment: BaseFragment?) {
        currentFragment = fragment
    }

    fun getCurrentFragment() = currentFragment
}