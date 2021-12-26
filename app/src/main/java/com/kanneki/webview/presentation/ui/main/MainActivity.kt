package com.kanneki.webview.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.kanneki.webview.R
import com.kanneki.webview.databinding.ActivityMainBinding
import com.kanneki.webview.presentation.base.AppDialog
import com.kanneki.webview.utils.LogUtils
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    // Layout
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // ViewModel
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) return@addOnCompleteListener
            LogUtils.showDebugLog("onComplete: ${task.result}")
        }
    }

    override fun onBackPressed() {
        val navigationController = findNavController(R.id.main_fragment)
        when {
            navigationController.currentDestination?.id
                    != navigationController.graph.startDestination -> {
                super.onBackPressed()
            }
            viewModel.getCurrentFragment()?.onBackPressed() == true -> return
            else -> {
                AppDialog.exitDialog(this) { type ->
                    if (type) super.onBackPressed()
                }
            }
        }
    }
}