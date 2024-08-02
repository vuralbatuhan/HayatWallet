package com.example.hayatwallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hayatwallet.databinding.FragmentProfileBinding
import com.example.hayatwallet.sharedPreferences.SharedPreferencesHayat
import com.example.hayatwallet.viewModels.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = SharedPreferencesHayat.getToken(requireContext())
        viewModel.userDeatils(token)
        viewModel.userInfermation.observe(viewLifecycleOwner, Observer { response ->
            binding.firstAndLastName.updateUI(
                "Full name",
                "${response?.item?.firstName} + ${response?.item?.lastName}"
            )
            binding.phoneNumber.updateUI("Phone number", response?.item?.phoneNumber.toString())
            binding.email.updateUI("email", response?.item?.email.toString())
        })

    }
}
