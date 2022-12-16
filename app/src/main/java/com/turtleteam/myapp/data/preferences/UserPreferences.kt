package com.turtleteam.myapp.data.preferences

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    companion object {
        private const val PREFS_NAME = "PREFERENCE"
        private const val USER_TOKEN = "savedusertoken"
    }

    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getUserId(token: String) = preferences.edit().putString(USER_TOKEN, token).apply()

    fun setUserId(): String? = preferences.getString(USER_TOKEN, "0")
}