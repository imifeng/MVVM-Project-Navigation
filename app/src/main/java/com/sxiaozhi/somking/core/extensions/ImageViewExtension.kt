package com.sxiaozhi.somking.core.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sxiaozhi.somking.R

fun ImageView.loadRoundAvatar(context: Context, url: String?, error: Int = R.mipmap.ic_avatar) {
    Glide.with(context)
        .load(url)
        .dontAnimate()
        .error(error)
        .priority(Priority.HIGH)
        .into(this)
}

fun ImageView.loadImage(context: Context, url: String?) {
    Glide.with(context)
        .load(url)
        .dontAnimate()
        .priority(Priority.HIGH)
        .into(this)
}

fun ImageView.loadImageFitCenter(context: Context, url: String?) {
    val requestOptions =
        RequestOptions.fitCenterTransform()
    Glide.with(context)
        .load(url)
        .dontAnimate()
        .priority(Priority.HIGH)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadImageRoundCorner(context: Context, url: String?, roundedCorners: Int) {
    val requestOptions =
        RequestOptions().transform(CenterCrop(), RoundedCorners(roundedCorners))
    Glide.with(context)
        .load(url)
        .dontAnimate()
        .priority(Priority.HIGH)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadGif(gifDrawable: Int, loopCount: Int = GifDrawable.LOOP_FOREVER) {
    Glide.with(context).asGif().load(gifDrawable)
        .listener(object : RequestListener<GifDrawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<GifDrawable>,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: GifDrawable,
                model: Any,
                target: Target<GifDrawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                resource.setLoopCount(loopCount)
                return false
            }

        }).into(this).clearOnDetach()
}

fun clearMemory(context: Context) {
    Glide.get(context).clearMemory()
}
