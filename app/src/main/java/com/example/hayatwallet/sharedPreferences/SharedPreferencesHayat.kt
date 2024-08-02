package com.example.hayatwallet.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHayat {
    private const val sharedPreferencesName = "user_token"
    private const val tokenLogin = "token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(tokenLogin, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(tokenLogin, null)
    }
}