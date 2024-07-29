package com.example.hayatwallet.network

import com.example.hayatwallet.data.APIParameter
import com.example.hayatwallet.data.HayatResponse
import com.example.hayatwallet.data.HayatResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginAPI {
    @POST("/User/signin")
    fun loginWithToken(@Body parameters : APIParameter) : Call<HayatResponse>

    @GET("/user/viewuser")
    fun getUser(@Header("Authorization") authorization: String) : Call<HayatResult>
}