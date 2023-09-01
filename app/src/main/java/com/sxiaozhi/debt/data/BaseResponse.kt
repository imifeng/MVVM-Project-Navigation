package com.sxiaozhi.debt.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// {
//    "code": 4444,
//    "msg": "没有token",
//    "time": "1656903204",
//    "data": {
//        "tutorialUrl": "http://wifi.3xiaozhi.com/guide.html",
//        "jia": "1",
//        "sn": "s09dbz8AMS"
//    },
//    "jia": "1",
//    "sn": "s09dbz8AMS"
//}
@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val code: Int,
    @Json(name = "msg")
    val message: String?,
    val data: T? = null,
    val jia: String?, // 当前版本是否正在审核中，如果返回1，是审核版本，广告策略降级，否则正常显示广告
    val sn: String?, // 当前版本是否正在审核中，如果返回 s09dbz8AMS，是审核关闭状态，显示广告一系列， 否则不显示广告
)