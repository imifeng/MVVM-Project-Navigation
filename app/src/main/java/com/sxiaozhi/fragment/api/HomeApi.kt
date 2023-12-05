package com.sxiaozhi.fragment.api

import com.sxiaozhi.fragment.data.*
import retrofit2.http.*

interface HomeApi {

    /**
     * 网络请求方法:@GET、@POST、@PUT、@DELETE、@HEAD(常用)
     * 网络请求标记: @FormUrlEncoded、@Multipart、@Streaming
     * 网络请求参数: @Header &、@Headers、 @Body、@Field 、 @FieldMap、@Part 、 @PartMap、@Query、@QueryMap、@Path、@Url
     */

    @GET("debt/index/home")
    suspend fun getHomeData(): BaseResponse<HomeBean>

    // 学法 /debt/index/study
    @GET("debt/index/study")
    suspend fun getStudyData(@Query("type") type: String): BaseResponse<StudyBean>

    // 订单 /debt/index/myorder
    @GET("debt/index/myorder")
    suspend fun getOrderData(@Query("page") page: String): BaseResponse<OrderBean>

    // 案例详情 /debt/index/casedetail
    // 计算器 /debt/index/calculator


    // 资料提交页 /debt/index/initsubmit
    @GET("debt/index/initsubmit")
    suspend fun getInitSubmitData(): BaseResponse<Any>

    // 提交申请 /debt/index/submit
    @FormUrlEncoded
    @POST("debt/index/submit")
    suspend fun modifyUserAvatar(
        @Field("username") username: String,
        @Field("mobile") mobile: String,
        @Field("platform") platform: String,
        @Field("amount") amount: String,
        @Field("time") time: String,
        @Field("refer") refer: String?,
    ): BaseResponse<Any>


}