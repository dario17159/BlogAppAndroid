package com.san.juan.app.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.san.juan.app.blogapp.R
import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.data.remote.auth.LoginDataSource
import com.san.juan.app.blogapp.databinding.FragmentLoginBinding
import com.san.juan.app.blogapp.domain.auth.LoginRepoImpl
import com.san.juan.app.blogapp.presentation.auth.LoginScreenViewModel
import com.san.juan.app.blogapp.presentation.auth.LoginScreenViewModelFactory


class LoginFragment : Fragment(R.layout.fragment_login) {


    lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authViewModel by viewModels<LoginScreenViewModel> {
        LoginScreenViewModelFactory(
            LoginRepoImpl(
                LoginDataSource()
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
        doSignUp()

    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homScreenFragment)
        }
    }

    private fun doLogin() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)

        }
    }

    private fun doSignUp() {
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateCredentials(emai: String, password: String) {
        if (emai.isEmpty()) {
            binding.etEmail.error = "E-mail is empty"
            return
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Password is empty"
            return
        }
    }

    private fun signIn(email: String, password: String) {
        authViewModel.signIn(email, password).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignIn.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homScreenFragment)
                }
                is Resource.Failure -> {

                    binding.progressBar.visibility = View.GONE
                    binding.btnSignIn.visibility = View.VISIBLE

                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


}