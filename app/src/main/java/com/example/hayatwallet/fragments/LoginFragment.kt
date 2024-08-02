package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.hayatwallet.databinding.FragmentLoginBinding
import com.example.hayatwallet.sharedPreferences.SharedPreferencesHayat
import com.example.hayatwallet.viewModels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private val username = "ararat2@oktein.com"
    private val password = "123456789Aa@"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            viewModel.loginResponse(username, password)

            viewModel.userLogin.observe(viewLifecycleOwner) { response ->
                if (response != null && response.item.isSuccess == true) {
                    SharedPreferencesHayat.saveToken(requireActivity(), response.item.token.toString())
                    val action = LoginFragmentDirections.actionLoginFragmentToForTabLayoutFragment()
                    view.findNavController().navigate(action)
                    Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
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

}
