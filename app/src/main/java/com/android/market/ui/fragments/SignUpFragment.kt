package com.android.market.ui.fragments
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.market.R
import com.android.market.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment: Fragment(){
    private val binding  : FragmentSignupBinding by lazy { FragmentSignupBinding.inflate(layoutInflater) }

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

        binding.registerButton.setOnClickListener {
            register(it)

            //it.findNavController().navigate(R.id.action_signUpFragment_to_loginPageFragment2)
        }

        binding.alreadyText.setOnClickListener{
            it.findNavController().navigate(R.id.action_signUpFragment_to_loginPageFragment2)
        }
    }

    private fun register(view: View) {
        val username = binding.usernameInput.text.toString()
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()
        if(email.isEmpty() || password.isEmpty()) {
            //(Toast.makeText(this, "this is required", Toast.LENGTH_SHORT)).show()
            return
        }

        Log.d("SignUpFragment", username)
        Log.d("SignUpFragment", email)
        Log.d("SignUpFragment", password)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if(it.isSuccessful){
                    view.findNavController().navigate(R.id.action_signUpFragment_to_loginPageFragment2)
                    Log.d("signup", "Successfully created user with uid: ${it.result?.user?.uid}")

                }
                else{
                    return@addOnCompleteListener
                }

            }
            .addOnFailureListener{
                Log.d("signup", "Failed to create user: ${it.message}")
            }

    }



}
