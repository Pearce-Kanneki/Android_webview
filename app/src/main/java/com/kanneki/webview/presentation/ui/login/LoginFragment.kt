package com.kanneki.webview.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kanneki.webview.R
import com.kanneki.webview.databinding.FragmentLoginBinding
import com.kanneki.webview.presentation.base.BaseFragment

class LoginFragment: BaseFragment() {

    private var binding: FragmentLoginBinding? = null
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginBackButton?.setOnClickListener { findNavController().popBackStack() }
        binding?.loginToolbar?.setNavigationOnClickListener{ findNavController().popBackStack()}
        binding?.loginSubmitButton?.setOnClickListener {
            viewModel.loginData(
                binding?.loginAccount?.text.toString(),
                binding?.loginPassword?.text.toString()
            )
        }
        binding?.loginForgot?.setOnClickListener {
            changerPage(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}