package com.sxiaozhi.somking.core.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import java.io.File


/**
 * Given a context, fetch the main looper so that we can post messages on the UI thread
 *
 * @param function anonymous function to run in the Handler
 */
fun Context.getMainThread(function: () -> Unit) {
    Handler(this.mainLooper).post(function)
}

/**
 * Show a long toast message on the UI with a given string placeholder
 *
 * This method is deprecated and should not be used except for in testing. Please use the
 * [makeLongToast] with a resource id instead.
 *
 * @param string the message to be displayed
 */
fun Context.makeLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

/**
 * Show a long toast message on the UI with a given string resource id
 *
 * @param string the resource id to be displayed
 */
fun Context.makeLongToast(@StringRes string: Int) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

/**
 * Show a short toast message on the UI with a given string placeholder
 *
 * This method is deprecated and should not be used except for in testing. Please use the
 * [makeShortToast] with a resource id instead.
 *
 * @param string the message to be displayed
 */
fun Context.makeShortToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

/**
 * Show a short toast message on the UI with a given string resource id
 *
 * @param string the resource id to be displayed
 */
fun Context.makeShortToast(@StringRes string: Int) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.getDrawableRes(@DrawableRes drawableRes: Int) =
    ContextCompat.getDrawable(this, drawableRes)

fun Context.getColorRes(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

/**
 * A method to hide the soft keyboard when on the screen
 *
 * @param view - this can literally be any view on screen
 */
fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * 复制内容到剪切板
 *
 * @param copyStr
 * @return
 */
fun Context.copyString(copyStr: String): Boolean {
    return try {
        //获取剪贴板管理器
        val cm: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val clipData = ClipData.newPlainText("Label", copyStr)
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(clipData)
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.getClipboardString(): String? {
    return try {
        //获取剪贴板管理器
        val cm: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cm.primaryClip?.getItemAt(0)?.text?.toString()
    } catch (e: Exception) {
        null
    }
}

fun Context.refreshDownloadFile(file: File) {
    sendBroadcast(
        Intent(
            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
            Uri.parse("file://${file}")
        )
    )
}