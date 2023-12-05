package com.sxiaozhi.fragment.core.constant

import com.sxiaozhi.fragment.R

object Constant {
    const val DELAY_SHORT = 200L

    const val DELAY_MIDDLE = 500L

    const val DELAY_LONG = 1000L

    const val DELAY_TOO_LONG = 2000L

    const val DELAY_TO_TIMER_SECOND = 1

    const val ALPHA_NUM_REGEX_PATTERN = "^[a-zA-Z0-9]+$"

    const val QQ = "3627719320"

    // 广告开关
    const val isADAvailable = true

    // 微信：
    const val Weixin_AppID = "wx71f4c7f8beaad146"

    val tabNavigationList: List<Int>
        get() = listOf(
            R.id.navigation_home,
            R.id.navigation_tools,
            R.id.navigation_science,
            R.id.navigation_profile,
        )
}

