package com.sxiaozhi.debt.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(
    val code: Int,
    val message: String?,
    val error: String?
)