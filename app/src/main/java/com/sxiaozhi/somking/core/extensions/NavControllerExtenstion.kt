package com.sxiaozhi.somking.core.extensions

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.sxiaozhi.somking.utils.Logger

const val TAG = "Navigation"

fun NavController.navigateWithCatch(resId: Int) {
    try {
        navigate(resId)
    } catch (e: Exception) {
        Logger.d(TAG, e.toString())
    }
}

fun NavController.navigateWithCatch(resId: Int, args: Bundle?) {
    try {
        navigate(resId, args)
    } catch (e: Exception) {
        Logger.d(TAG, e.toString())
    }
}

fun NavController.navigateWithCatch(deepLink: Uri) {
    try {
        navigate(deepLink)
    } catch (e: Exception) {
        Logger.d(TAG, e.toString())
    }
}

fun NavController.navigateWithCatch(resId: Int, args: Bundle?, navigatorExtras: Navigator.Extras?) {
    try {
        navigate(resId, args, null, navigatorExtras)
    } catch (e: Exception) {
        Logger.d(TAG, e.toString())
    }
}
