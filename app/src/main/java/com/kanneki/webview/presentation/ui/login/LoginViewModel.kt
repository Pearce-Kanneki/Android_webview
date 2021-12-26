package com.kanneki.webview.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.kanneki.webview.utils.LogUtils

class LoginViewModel:ViewModel() {

//    private var _account = MutableStateFlow<String?>(null)
//    val account = _account.asStateFlow()
//    private var _password = MutableStateFlow<String?>(null)
//    val password = _password.asStateFlow()

    fun loginData(account: String?, password:String?) {
        LogUtils.showDebugLog("Account: $account")
        LogUtils.showDebugLog("Password: $password")
    }
}