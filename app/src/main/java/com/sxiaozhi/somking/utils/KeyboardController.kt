package com.sxiaozhi.somking.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardController(
    private val activity: Activity
) {
    private val inputMethodManager: InputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    /**
     * Hide the keyboard
     */
    fun hide() {
        activity.currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken, 0
            )
        }
    }

    fun show(view: View) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
