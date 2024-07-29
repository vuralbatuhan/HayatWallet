package com.example.hayatwallet.data

data class HayatResponse (val item: Item)
data class HayatResult (val item: GetItem)

data class APIParameter (val username: String?, val password: String?)

data class Item (val token: String, var isSuccess: Boolean )

data class GetItem (val userName: String, val firstName: String, val lastName: String, val phoneNumber: String, val email: String)