package com.sxiaozhi.somking.repository

import com.sxiaozhi.somking.api.HomeApi
import com.sxiaozhi.somking.data.BaseResponse
import com.sxiaozhi.somking.data.HomeBean
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {
    override suspend fun getHome(): BaseResponse<HomeBean> {
        return homeApi.getHomeData()
    }

    override suspend fun getInitSubmitData(): BaseResponse<Any> {
        return homeApi.getInitSubmitData()
    }
}


interface HomeRepository {

    suspend fun getHome(): BaseResponse<HomeBean>

    suspend fun getInitSubmitData(): BaseResponse<Any>

}