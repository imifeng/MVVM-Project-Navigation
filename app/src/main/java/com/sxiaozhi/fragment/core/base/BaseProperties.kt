package com.sxiaozhi.fragment.core.base

import androidx.annotation.DrawableRes
import com.sxiaozhi.fragment.R

data class BaseProperties(
    val hasStatusBar: Boolean? = true,
    @DrawableRes val rootBackgroundRes: Int? = R.color.color_F5F5F5
)
