package com.sxiaozhi.debt.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.sxiaozhi.debt.BuildConfig
import javax.inject.Singleton

class SharedPrefService(val context: Context) {

    companion object {
        private const val TAG = "SharedPrefService"

        const val REVIEW_KEY: String = "s09dbz8AMS"

        private const val SP_PREFIX = "${BuildConfig.APPLICATION_ID}.SHARED_PREF"
        private const val SHARED_PREF = "${SP_PREFIX}_${BuildConfig.BUILD_TYPE}"

        private const val DEVICE_ID = "$SP_PREFIX.DEVICE_ID"
        private const val IS_REVIEW = "$SP_PREFIX.IS_REVIEW"
        private const val SN_KEY = "$SP_PREFIX.SN_KEY"
        private const val USER = "$SP_PREFIX.USER"
        private const val USER_ID = "$SP_PREFIX.USER_ID"
        private const val USER_JSON = "$SP_PREFIX.USER_JSON"
        private const val USER_TOKEN = "$SP_PREFIX.USER_TOKEN"
        private const val USER_GOLD = "$SP_PREFIX.USER_GOLD"
        private const val USER_VIP = "$SP_PREFIX.USER_VIP"

        private const val APP_PRIVACY = "$SP_PREFIX.APP_PRIVACY"

        private const val APP_HOME_POPUP = "$SP_PREFIX.APP_HOME_POPUP"
        private const val DAILY_SIGN = "$SP_PREFIX.DAILY_SIGN"
        private const val CLIPBOARD_COPY = "$SP_PREFIX.CLIPBOARD_COPY"
    }

    private val sp: SharedPreferences = context.getSharedPreferences(
        SHARED_PREF,
        Context.MODE_PRIVATE
    )

    val version: Int = BuildConfig.VERSION_CODE
    val versionName: String = BuildConfig.VERSION_NAME

    var privacyApp: Boolean
        get() = sp.getBoolean(APP_PRIVACY, false)
        set(value) {
            sp.edit {
                putBoolean(APP_PRIVACY, value)
            }
        }

    var homePopupApp: Boolean
        get() = sp.getBoolean(APP_HOME_POPUP, true)
        set(value) {
            sp.edit {
                putBoolean(APP_HOME_POPUP, value)
            }
        }

    var lastDailySignDate: String
        get() = sp.getString(DAILY_SIGN, "")?:""
        set(value) {
            sp.edit {
                putString(DAILY_SIGN, value)
            }
        }

    var deviceId: String
        get() = sp.getString(DEVICE_ID, "") ?: ""
        set(value) {
            sp.edit {
                putString(DEVICE_ID, value)
            }
        }

    /**
     * 检索用户id
     */
    var userId: String
        get() = sp.getString(USER_ID, deviceId) ?: deviceId
        set(value) {
            sp.edit {
                putString(USER_ID, value)
            }
        }

    /**
     * 检索用户 avatar
     */
//    fun userAvatar(): String = getCurrentUser()?.avatar ?: ""

    /**
     * 检索用户token
     */
    var token: String
        get() = sp.getString(USER_TOKEN, "") ?: ""
        set(value) {
            sp.edit {
                putString(USER_TOKEN, value)
            }
        }

    var isReview: String
        get() = sp.getString(IS_REVIEW, "1") ?: "1"
        set(value) {
            sp.edit {
                putString(IS_REVIEW, value)
            }
        }

    var snKey: String
        get() = sp.getString(SN_KEY, "0") ?: "0"
        set(value) {
            sp.edit {
                putString(SN_KEY, value)
            }
        }

    var gold: String
        get() = sp.getString(USER_GOLD, "0") ?: "0"
        set(value) {
            sp.edit {
                putString(USER_GOLD, value)
            }
        }

    var clipboardText: String?
        get() = sp.getString(CLIPBOARD_COPY, null)
        set(value) {
            sp.edit {
                putString(CLIPBOARD_COPY, value)
            }
        }

    fun hasGold() = gold.toIntOrNull() ?: 0 > 0

    /**
     * 检索用户Json
     */
    private var userJson: String
        get() = sp.getString(USER_JSON, "") ?: ""
        set(value) {
            sp.edit {
                putString(USER_JSON, value)
            }
        }

//    fun saveUser(bean: User) {
//        userId = bean.id
//        val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
//        userJson = jsonAdapter.toJson(bean)
//    }

//    fun updateUserAvatar(avatar: String) {
//        val bean = getCurrentUser()
//        bean?.let {
//            it.avatar = avatar
//            saveUser(it)
//        }
//    }

//    fun updateUserNick(nick: String) {
//        val bean = getCurrentUser()
//        bean?.let {
//            it.nickname = nick
//            saveUser(it)
//        }
//    }


    fun logout() {
        userJson = ""
        token = ""

        // remove current user
        sp.edit {
            remove(USER)
        }
    }

//    fun getCurrentUser(): User? {
//        try {
//            val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
//            return jsonAdapter.fromJson(userJson)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }

    fun isLoginAuth() = token.isNotEmpty()

//    fun getChannel() = context.getApplicationMetaValue("UMENG_CHANNEL", "default")
}