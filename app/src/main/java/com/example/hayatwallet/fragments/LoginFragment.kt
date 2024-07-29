package com.example.hayatwallet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.hayatwallet.databinding.FragmentLoginBinding
import com.example.hayatwallet.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val username = "ararat2@oktein.com"
    private val password = "123456789Aa@"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            viewModel.loginWithToken(username, password)
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.userDetails.observe(viewLifecycleOwner) { userDetails ->
            if (userDetails != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToMainHomeFragment()
                view.findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
