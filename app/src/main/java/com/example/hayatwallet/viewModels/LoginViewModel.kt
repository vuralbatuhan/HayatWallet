package com.example.hayatwallet.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hayatwallet.data.APIParameter
import com.example.hayatwallet.data.HayatResponse
import com.example.hayatwallet.data.Item
import com.example.hayatwallet.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val userLogin = MutableLiveData<HayatResponse?>()

    fun loginResponse(username: String, password: String) {

        Network.service.loginWithToken(APIParameter(username, password))
            .enqueue(object : Callback<HayatResponse> {
                override fun onResponse(
                    call: Call<HayatResponse>,
                    response: Response<HayatResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        userLogin.postValue(response.body())
                    } else {
                        userLogin.postValue(response.body())
                    }

                }

                override fun onFailure(call: Call<HayatResponse>, t: Throwable) {
                    userLogin.postValue(HayatResponse(Item("", false)))

                }

            })
    }
}
