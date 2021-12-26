package com.kanneki.webview.presentation.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kanneki.webview.databinding.FragmentForgotPasswordBinding
import com.kanneki.webview.presentation.base.BaseFragment

class ForgotPasswordFragment: BaseFragment() {

    private var binding: FragmentForgotPasswordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fotgotToolbar?.setNavigationOnClickListener{ findNavController().popBackStack()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}