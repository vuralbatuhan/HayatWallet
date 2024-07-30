package com.example.hayatwallet.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hayatwallet.data.HayatResult
import com.example.hayatwallet.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    val userInfermation = MutableLiveData<HayatResult?>()

    fun userDeatils(token: String?) {
        Network.service.getUser("Bearer $token").enqueue(object : Callback<HayatResult> {
            override fun onResponse(call: Call<HayatResult>, response: Response<HayatResult>) {
                if (response.isSuccessful && response.body() != null) {
                    userInfermation.postValue(response.body())
                } else {
                    userInfermation.postValue(null)
                }
            }

            override fun onFailure(call: Call<HayatResult>, t: Throwable) {
                userInfermation.postValue(null)
            }
        })
    }
}