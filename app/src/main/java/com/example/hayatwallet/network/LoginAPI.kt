package com.example.hayatwallet.network

import com.example.hayatwallet.data.APIParameter
import com.example.hayatwallet.data.HayatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/User/signin")
    fun loginWithToken(@Body parameters : APIParameter) : Call<HayatResponse>
}