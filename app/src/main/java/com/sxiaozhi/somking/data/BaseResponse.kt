package com.sxiaozhi.somking.data

import com.google.gson.annotations.SerializedName

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
data class BaseResponse<T>(
    val code: Int,
    @field:SerializedName("msg") val message: String?,
    val data: T? = null
)