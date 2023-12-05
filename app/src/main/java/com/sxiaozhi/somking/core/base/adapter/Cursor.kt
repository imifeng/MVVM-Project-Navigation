package com.sxiaozhi.somking.core.base.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cursor(
    val afterCursor: Int?,
    val beforeCursor: Int?,
    var total: Int?
) : Parcelable
