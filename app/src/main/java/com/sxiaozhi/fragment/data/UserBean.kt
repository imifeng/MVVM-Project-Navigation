package com.sxiaozhi.fragment.data

import com.google.gson.annotations.SerializedName

data class UserBean(
    @field:SerializedName("uid")
    val id: String?,
    val token: String?,
    var avatar: String?,
    var nickname: String?,
    val mobile: String?,
    val tutorialUrl: String?,
    val inviteUrl: String?,
) {
    fun toUser(): User? {
        return if (id.isNullOrEmpty()) {
            null
        } else User(
            id = id,
            token = token ?: "",
            avatar = avatar ?: "",
            nickname = nickname ?: "",
            mobile = mobile ?: ""
        )
    }
}

data class User(
    val id: String,
    var token: String,
    var avatar: String,
    var nickname: String?,
    val mobile: String,
)