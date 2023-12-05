package com.sxiaozhi.somking.api

import com.sxiaozhi.somking.data.BaseResponse
import com.sxiaozhi.somking.data.UploadBean
import com.sxiaozhi.somking.data.UserBean
import retrofit2.http.*

interface UserApi {

    /**
     * 网络请求方法:@GET、@POST、@PUT、@DELETE、@HEAD(常用)
     * 网络请求标记: @FormUrlEncoded、@Multipart、@Streaming
     * 网络请求参数: @Header &、@Headers、 @Body、@Field 、 @FieldMap、@Part 、 @PartMap、@Query、@QueryMap、@Path、@Url
     */

    // 微信授权code登录注册 / 接收微信code，从微信拿到OPENID，如果老用户就直接登录成功，如果是新用户
    @FormUrlEncoded
    @POST("love2/user/wxlogin")
    suspend fun loginWithWX(@Field("code") code: String): BaseResponse<UserBean>

    @FormUrlEncoded
    @POST("debt/user/onekeylogin")
    suspend fun loginWithAli(@Field("alitoken") token: String): BaseResponse<UserBean>

    // 使用短信登录注册, 如果老用户就直接登录成功，如果是新用户
    // code	string	否	验证码
    // mobile	string	否	手机号码
    @FormUrlEncoded
    @POST("debt/user/smslogin")
    suspend fun loginWithSMS(
        @Field("mobile") mobile: String,
        @Field("code") code: String
    ): BaseResponse<UserBean>

    // 发送短信 /walk/user/sendsms
    // mobile	string	否	手机号码
    @FormUrlEncoded
    @POST("debt/user/sendsms")
    suspend fun sendSMS(
        @Field("mobile") mobile: String
    ): BaseResponse<Any>

    // 用户信息
    @GET("debt/user/userinfo")
    suspend fun getUserInfo(): BaseResponse<UserBean>

    @FormUrlEncoded
    @POST("debt/user/modifyuser")
    suspend fun modifyUserAvatar(
        @Field("avatar") avatar: String
    ): BaseResponse<Any>

    @FormUrlEncoded
    @POST("love2/user/modifyuser")
    suspend fun modifyUserNick(
        @Field("nickname") nickname: String
    ): BaseResponse<Any>


    // 上传头像
    @FormUrlEncoded
    @POST("debt/common/upload")
    suspend fun uploadPic(@Field("image") imageBytes: String): BaseResponse<UploadBean>

    // 问题反馈 /user/feed
    // type	integer	是	类型:1=其他问题,2=功能建议,3=异常反馈,4=版权反馈
    // contact	string	是	联系方式
    // content	string	是	内容
    // images	string	是	图片[逗号拼接]
    @FormUrlEncoded
    @POST("debt/user/feed")
    suspend fun postFeedback(
        @Field("type") type: Int,
        @Field("content") content: String,
        @Field("contact") contact: String,
        @Field("images") images: String
    ): BaseResponse<Any>

    @POST("debt/user/remove")
    suspend fun removeUser(): BaseResponse<Any>

}