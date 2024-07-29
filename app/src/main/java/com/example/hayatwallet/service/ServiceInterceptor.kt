package com.example.hayatwallet.service

import com.example.hayatwallet.data.APIParameter
import com.example.hayatwallet.data.HayatResponse
import com.example.hayatwallet.network.Network
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class ServiceInterceptor : Interceptor {
    var tokenT: String? = null
    var username: String? = null
    var password: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        Network.service.loginWithToken(APIParameter(username, password)).enqueue(object :
            Callback<HayatResponse> {
            override fun onResponse(call: Call<HayatResponse>, response: retrofit2.Response<HayatResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    //_loginResponse.value = response.body()
                    tokenT = response.body()!!.item.token
                    tokenT?.let {
                        request = request.newBuilder()
                            .addHeader("Authorization", "Bearer $tokenT")
                            .build()
                    }
                    Network.serviceInterceptor.tokenT = tokenT
                }
            }

            override fun onFailure(call: Call<HayatResponse>, t: Throwable) {
            }
        })

        return chain.proceed(request)
    }
}
