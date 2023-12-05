package com.sxiaozhi.fragment.core.extensions

import com.sxiaozhi.fragment.core.base.DataState
import com.sxiaozhi.fragment.core.base.ErrorState
import com.sxiaozhi.fragment.core.base.LoginState
import com.sxiaozhi.fragment.data.BaseResponse

fun Int.toCodeTrue(): Boolean = this == 1

fun checkLoginState(code: Int, message: String?) = code in arrayOf(4444, 401, 403) ||
        message?.contains("token") == true ||
        message?.contains("没有用户信息") == true ||
        message?.contains("Unauthorized") == true


fun <T : Any> BaseResponse<T>.toConvertData(successCallback: ((body: BaseResponse<T>) -> DataState), checkLogin: Boolean = false): DataState =
    when {
        checkLogin && checkLoginState(code, message) -> LoginState

        code.toCodeTrue() -> successCallback.invoke(this)

        else -> ErrorState(code, message)
    }

/**
 * Allow checking if the input values are not null. If values are not null then the function block
 * is going to be executed
 *
 * @param T1 first value type
 * @param T2 second value type
 * @param R return value type
 * @param p1 first value
 * @param p2 second value
 * @param block function block to be executed if input values are not null
 * @return the value to be returned if any
 */
inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}


/**
 * Allow checking if the input values are not null. If values are not null then the function block
 * is going to be executed
 *
 * @param T1 first value type
 * @param T2 second value type
 * @param R return value type
 * @param p1 first value
 * @param p2 second value
 * @param block function block to be executed if input values are not null
 * @return the value to be returned if any
 */
inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    block: (T1, T2, T3) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}