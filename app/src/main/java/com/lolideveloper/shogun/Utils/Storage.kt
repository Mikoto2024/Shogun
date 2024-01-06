package com.lolideveloper.shogun.Utils

import android.content.Context
import javax.inject.Inject

class Storage @Inject constructor(context: Context) {
    val sharedpref = context.getSharedPreferences("Data", 0)

    // Language
    private val Shared_Language = "LG"

    // Menu State
    private val SHARED_MENU_STATE = "Menu_State"
    private val SHARED_LANGUAGE_STATE = "Language_State"

    // Fragment State
    private val SHARED_STATE = "state"

    // Data
    private val SHARED_USER = "user"
    private val SHARED_PASSWORD = "pass"
    private val SHARED_CODE = "code"

    /* Data  */
    fun svUser(user: String?, pass: String?, code: String?) {
        if (user != null) {
            sharedpref.edit().putString(SHARED_USER, user).apply()
        }
        if (pass != null) {
            sharedpref.edit().putString(SHARED_PASSWORD, pass).apply()
        }
        if (code != null) {
            sharedpref.edit().putString(SHARED_CODE, code).apply()
        }
    }

    fun getUser(i: Int): String {
        if (i == 0) {
            return sharedpref.getString(SHARED_USER, "").toString()
        }
        if (i == 1) {
            return sharedpref.getString(SHARED_PASSWORD, "").toString()
        }
        if (i == 2) {
            return sharedpref.getString(SHARED_CODE, "").toString()
        }
        return ""
    }

    /* Menu State */
    fun getSate(i: Int): String {
        if (i == 1) {
            val id = sharedpref.getString(SHARED_MENU_STATE, "Default {Xml}")
            return id.toString()
        }
        if (i == 2) {
            val id = sharedpref.getString(SHARED_LANGUAGE_STATE, "Default {English}")
            return id.toString()
        }
        return "Default {Xml}"
    }

    fun svState(i: Int, string: String) {
        if (i == 1) {
            sharedpref.edit().putString(SHARED_MENU_STATE, string).apply()
        }
        if (i == 2) {
            sharedpref.edit().putString(SHARED_LANGUAGE_STATE, string).apply()
        }
    }

    /* Language */
    fun svLanguage(lg: String) {
        sharedpref.edit().putString(Shared_Language, lg).apply()
    }

    fun getLanguage(): String {
        val language = sharedpref.getString(Shared_Language, "en")

        return language.toString()
    }

    /* Fragment Dialog State */
    fun svPager(i: String) {
        sharedpref.edit().putString(SHARED_STATE, i).apply()
    }

    fun getPager(): String {
        return sharedpref.getString(SHARED_STATE, "0").toString()
    }
}