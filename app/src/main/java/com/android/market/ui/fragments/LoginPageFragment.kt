package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.market.R
import com.android.market.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginPageFragment: Fragment() {
    private val binding  : FragmentLoginBinding  by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doNotAccount.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginPageFragment2_to_signUpFragment)
        }
        binding.loginButton.setOnClickListener{ vw ->
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        vw.findNavController().navigate(R.id.action_loginPageFragment2_to_mainActivity)
                        activity?.finish()
                    }
                    else{
                        return@addOnCompleteListener
                    }
                }


        }
    }

}