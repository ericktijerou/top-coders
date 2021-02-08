package com.ericktijerou.topcoders.data.local.system

import android.content.Context
import android.content.SharedPreferences
import com.ericktijerou.topcoders.core.EMPTY
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.ericktijerou.topcoders.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
        private const val PREF_KEY_LAST_USER_CURSOR = "last_user_cursor"
    }

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = pref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = pref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    var lastUserCursor: String?
        get() = pref.getString(PREF_KEY_LAST_USER_CURSOR, EMPTY)
        set(lastCursor) = pref.edit().putString(PREF_KEY_LAST_USER_CURSOR, lastCursor).apply()
}