package com.example.creativepantry

import android.content.Context
import android.content.SharedPreferences

object UsuarioPreferences {
    private const val PREF_NAME = "UsuarioPreferences"
    private const val USERNAME_KEY = "username"

    fun saveUsername(context: Context, username: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USERNAME_KEY, username)
        editor.apply()
    }

    fun getUsername(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USERNAME_KEY, null)
    }

    fun clearUsername(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(USERNAME_KEY)
        editor.apply()
    }
}

