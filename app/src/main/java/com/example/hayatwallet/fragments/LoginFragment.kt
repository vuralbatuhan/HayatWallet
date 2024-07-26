package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.hayatwallet.R
import com.example.hayatwallet.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_mainHomeFragment)
        }

        binding.editTextMusteriNo.setOnClickListener {
            binding.editTextMusteriNo.clearFocus()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment().apply {
            }
    }
}