package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.market.R
import com.android.market.databinding.FragmentLoginBinding

class LoginPageFragment: Fragment() {
    val binding  : FragmentLoginBinding  by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doNotAccount.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginPageFragment2_to_signUpFragment)
        }
        binding.loginButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginPageFragment2_to_mainActivity)
            activity?.finish()
        }
    }

}