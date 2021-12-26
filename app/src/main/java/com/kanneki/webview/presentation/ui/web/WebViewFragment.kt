package com.kanneki.webview.presentation.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.kanneki.webview.R
import com.kanneki.webview.databinding.FragmentWebviewBinding
import com.kanneki.webview.presentation.base.AppDialog
import com.kanneki.webview.presentation.base.BaseFragment
import com.kanneki.webview.utils.Constants
import com.kanneki.webview.utils.LogUtils
import com.kanneki.webview.utils.UrlManager


class WebViewFragment: BaseFragment() {

    private var bind: FragmentWebviewBinding? = null
    private val viewModel by viewModels<WebViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentWebviewBinding.inflate(layoutInflater)
        return bind?.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *  Init WebView
         */
        bind?.webView?.let { webView ->
            // WebView Setting
            webView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
            webView.webViewClient = setWebViewClient()
            webViewLoadUrl(UrlManager.TYPE_HOME)
        }
        navigationItemSelectedListener()
    }

    private fun navigationItemSelectedListener() {
        // TODO Navigation Item Click
        bind?.navigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> webViewLoadUrl(UrlManager.TYPE_HOME)
                R.id.menuCategory -> webViewLoadUrl(UrlManager.TYPE_CATEGORY)
                R.id.menuCollect -> {
                    LogUtils.showDebugLog("收藏")
                    AppDialog.messageDialog(requireActivity(), "收藏")
                }
                R.id.menuAccount -> webViewLoadUrl(UrlManager.TYPE_ACCOUNT)
                R.id.menuCart -> {
                    changerPage(R.id.action_webViewFragment_to_loginFragment)
                }
                else -> LogUtils.showErrorLog("Navigation Item")
            }
            true
        }
    }

    private fun setWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                LogUtils.showDebugLog("Url: ${request?.url.toString()}")
                request?.let { req ->
                    if (req.url.toString().indexOf(Constants.LOGIN_KEY) >= 0) {
                        changerPage(R.id.action_webViewFragment_to_loginFragment)
                    } else {
                        //view?.loadUrl(req.url.toString())
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                } // end let
                return true
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                // 不顯示web的footer header
                val javaScriptFunction = "javascript:(function() {" +
                        "document.getElementsByClassName('footer')[0].style.display='none'; " +
                        "})()"
                view?.loadUrl(javaScriptFunction)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (viewModel.getClearHistory()) {
                    viewModel.setClearHistory(false)
                    view?.clearHistory()
                }
                super.onPageFinished(view, url)
            }
        }
    }

    private fun webViewLoadUrl(type: Int) {
        bind?.webView?.loadUrl(UrlManager.getUrlByType(type))
        viewModel.setClearHistory(true)
    }

     override fun onBackPressed(): Boolean {
        if (bind?.webView?.canGoBack() == true) {
            bind?.webView?.goBack()
            return true
        }

        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind?.webView?.destroy()
        bind = null
    }
}