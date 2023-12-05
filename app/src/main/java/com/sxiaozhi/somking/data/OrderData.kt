package com.sxiaozhi.somking.data


data class OrderBean(
    val page: Int?,
    val totalPage: Int?,
    val orders: List<OrderInfo>?,
    val agent: AgentBean?
)

data class OrderInfo(
    val id: String,
    val amount: String?,
    val username: String?,
    val platform: String?,
    val time: String?,
    val mobile: String?,
    val refer: String?,
    val create_time: String?,
)