package com.sxiaozhi.somking.data


//            "url" " "https://xxxx/xxxx.jpg"
data class HomeBean(
    val agent: AgentBean?,
    val cases: List<CaseBean>?
)

data class CaseBean(
    val id: String,
    val name: String?,
    val avatar: String?,
    val nickname: String?,
    val type: String?,
    val platform: String?,
    val amount: String?,
    val result: String?,
)

