package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.hayatwallet.R
import com.example.hayatwallet.databinding.FragmentLoginDefinedBinding
import com.example.hayatwallet.sharedPreferences.SharedPreferencesHayat
import com.example.hayatwallet.viewModels.LoginViewModel
import com.example.hayatwallet.viewModels.ProfileViewModel

class LoginDefinedFragment : Fragment() {
    private lateinit var binding: FragmentLoginDefinedBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val username = "ararat2@oktein.com"
    private val password = "123456789Aa@"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginDefinedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = SharedPreferencesHayat.getToken(requireContext())
        profileViewModel.userDeatils(token)
        profileViewModel.userInfermation.observe(viewLifecycleOwner, Observer { response ->
            binding.textViewWelcomeUser.text = "WELCOME ${response?.item?.firstName}"
        })

        binding.buttonLogin.setOnClickListener {
            viewModel.loginResponse(username, password)

            viewModel.userLogin.observe(viewLifecycleOwner) { response ->
                if (response != null && response.item.isSuccess == true) {
                    SharedPreferencesHayat.saveToken(requireActivity(), response.item.token.toString())
                    val action = LoginDefinedFragmentDirections.actionLoginDefinedFragmentToForTabLayoutFragment()
                    view.findNavController().navigate(action)
                    Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}