package com.example.hayatwallet.data

data class HayatResponse (val item: Item)

data class APIParameter (val username: String, val password: String)

data class Item (val token: String, val isSuccess: Boolean )