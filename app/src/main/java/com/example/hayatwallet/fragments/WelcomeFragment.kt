package com.example.hayatwallet.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.hayatwallet.R
import com.example.hayatwallet.data.HayatResult
import com.example.hayatwallet.databinding.FragmentWelcomeBinding
import com.example.hayatwallet.network.Network
import com.example.hayatwallet.sharedPreferences.SharedPreferencesHayat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = SharedPreferencesHayat.getToken(requireContext())
        checkDefinedUser(token)

    }
    fun checkDefinedUser(token: String?) {
        Network.service.getUser("Bearer $token").enqueue(object : Callback<HayatResult> {
            override fun onResponse(call: Call<HayatResult>, response: Response<HayatResult>) {
                if (response.isSuccessful && response.body() != null) {
                    binding.buttonCustomer.setOnClickListener {
                        view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_loginDefinedFragment)
                    }
                } else {
                    binding.buttonCustomer.setOnClickListener {
                        view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_loginFragment)
                    }
                }
            }

            override fun onFailure(call: Call<HayatResult>, t: Throwable) {
                println("yanlissss")
            }
        })
    }

}