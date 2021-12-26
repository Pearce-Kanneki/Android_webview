package com.kanneki.webview.presentation.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.kanneki.webview.presentation.ui.main.MainViewModel


open class BaseFragment: Fragment() {

    protected val mainViewModel: MainViewModel by activityViewModels()

    /**
     *  NavController 換頁防呆
     */
    fun changerPage(@IdRes id: Int) {
        val currentDestination =
            (findNavController().currentDestination as? FragmentNavigator.Destination)?.className
                ?: (findNavController() as? DialogFragmentNavigator.Destination)?.className

        if (currentDestination == this.javaClass.name) {
            findNavController().navigate(id)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.setCurrentFragment(this)
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.setCurrentFragment(null)
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}