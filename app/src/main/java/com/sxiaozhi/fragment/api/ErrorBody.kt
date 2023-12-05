package com.sxiaozhi.fragment.api

data class ErrorBody(
    val code: Int,
    val message: String?,
    val error: String?
)