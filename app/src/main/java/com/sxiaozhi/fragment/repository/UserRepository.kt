package com.sxiaozhi.fragment.repository

import com.sxiaozhi.fragment.api.UserApi
import com.sxiaozhi.fragment.data.BaseResponse
import com.sxiaozhi.fragment.data.UploadBean
import com.sxiaozhi.fragment.data.UserBean
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getUserInfo(): BaseResponse<UserBean> {
        return userApi.getUserInfo()
    }

    override suspend fun uploadPic(imageBytes: String): BaseResponse<UploadBean> {
        return userApi.uploadPic(imageBytes)
    }

    override suspend fun sendSMS(mobile: String): BaseResponse<Any> {
        return userApi.sendSMS(mobile)
    }

    override suspend fun loginWithWX(code: String): BaseResponse<UserBean> {
        return userApi.loginWithWX(code)
    }

    override suspend fun loginWithSMS(mobile: String, code: String): BaseResponse<UserBean> {
        return userApi.loginWithSMS(mobile, code)
    }

    override suspend fun modifyUserAvatar(avatar: String): BaseResponse<Any> {
        return userApi.modifyUserAvatar(avatar)
    }

    override suspend fun modifyUserNick(nickname: String): BaseResponse<Any> {
        return userApi.modifyUserNick(nickname)
    }

    override suspend fun removeUser(): BaseResponse<Any> {
        return userApi.removeUser()
    }

    override suspend fun postFeedback(
        type: Int,
        content: String,
        contact: String,
        images: String
    ): BaseResponse<Any> {
        return userApi.postFeedback(type, content, contact, images)
    }

}


interface UserRepository {

    suspend fun getUserInfo(): BaseResponse<UserBean>

    suspend fun uploadPic(imageBytes: String): BaseResponse<UploadBean>

    suspend fun sendSMS(mobile: String): BaseResponse<Any>

    suspend fun loginWithWX(code: String): BaseResponse<UserBean>

    suspend fun loginWithSMS(mobile: String, code: String): BaseResponse<UserBean>

    suspend fun modifyUserAvatar(avatar: String): BaseResponse<Any>

    suspend fun modifyUserNick(nickname: String): BaseResponse<Any>

    suspend fun removeUser(): BaseResponse<Any>

    suspend fun postFeedback(
        type: Int,
        content: String,
        contact: String,
        images: String
    ): BaseResponse<Any>

}