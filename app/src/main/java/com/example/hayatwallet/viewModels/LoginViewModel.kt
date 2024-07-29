package com.example.hayatwallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hayatwallet.data.APIParameter
import com.example.hayatwallet.data.HayatResponse
import com.example.hayatwallet.data.HayatResult
import com.example.hayatwallet.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val loginResponseList = MutableLiveData<HayatResponse?>()
    val loginResponse: LiveData<HayatResponse?> get() = loginResponseList

    private val userDetailsList = MutableLiveData<HayatResult?>()
    val userDetails: LiveData<HayatResult?> get() = userDetailsList

    fun loginWithToken(username: String, password: String) {
        Network.service.loginWithToken(APIParameter(username, password)).enqueue(object :
            Callback<HayatResponse> {
            override fun onResponse(call: Call<HayatResponse>, response: Response<HayatResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    loginResponseList.value = response.body()
                    val token = response.body()!!.item.token
                    Network.serviceInterceptor.tokenT = token
                    getUserDetails(token)
                } else {
                    loginResponseList.value = null
                }
            }

            override fun onFailure(call: Call<HayatResponse>, t: Throwable) {
                loginResponseList.value = null
            }
        })
    }

    private fun getUserDetails(token: String) {
        Network.service.getUser("Bearer $token").enqueue(object : Callback<HayatResult> {
            override fun onResponse(call: Call<HayatResult>, response: Response<HayatResult>) {
                if (response.isSuccessful && response.body() != null) {
                    userDetailsList.value = response.body()
                } else {
                    userDetailsList.value = null
                }
            }

            override fun onFailure(call: Call<HayatResult>, t: Throwable) {
                userDetailsList.value = null
            }
        })
    }
}
