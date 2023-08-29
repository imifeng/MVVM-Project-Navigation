package com.sxiaozhi.debt.core.base

import androidx.annotation.DrawableRes
import com.sxiaozhi.debt.R

data class BaseProperties(
    val hasStatusBar: Boolean? = true,
    @DrawableRes val rootBackgroundRes: Int? = R.color.color_F5F5F5
)
