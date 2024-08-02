package com.example.hayatwallet.data

import com.example.hayatwallet.R
import java.io.Serializable


data class HayatResponse(val item: Item) : Serializable

data class HayatResult(val item: GetItem) : Serializable

data class APIParameter(val username: String?, val password: String?) : Serializable

data class Item(val token: String?, var isSuccess: Boolean?) : Serializable

data class GetItem(
    val userName: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val email: String?
) : Serializable

data class HayatCampaign(val title: String?, val imageUrl: String) : Serializable

data class HomeWallet(val marka: String, val money: String, val markasImage: Int, val time: String)

