package com.sxiaozhi.fragment.utils

import android.media.AudioAttributes
import android.media.MediaPlayer

class AudioPlayer {
    private var player: MediaPlayer? = null

    private var url: String = ""

    /**
     * Play desired media
     *
     * @param url - song reference
     */
    fun start(url: String, callbackFail: (() -> Unit)? = null) {
        if (url.isEmpty()) {
            callbackFail?.invoke()
            destroy()
            return
        }
        if (player == null) {
            player = MediaPlayer()
        }
        this.url = url
        try {
            player?.apply {
                reset()
                val attributes =
                    AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                setAudioAttributes(attributes)
                setDataSource(url)
                isLooping = true
                prepare()
                start()
            }
        } catch (e: Exception) {
            destroy()
            callbackFail?.invoke()
        }
    }

    fun start() {
        try {
            player?.start()
        } catch (e: Exception) {
        }
    }

    fun reStartAfterStop(callbackFail: (() -> Unit)? = null) {
        if (url.isNullOrEmpty()){
            callbackFail?.invoke()
        }else{
            try {
                player?.apply {
                    reset()
                    val attributes =
                        AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    setAudioAttributes(attributes)
                    setDataSource(url)
                    isLooping = true
                    prepare()
                    start()
                }
            } catch (e: Exception) {
                destroy()
                callbackFail?.invoke()
            }
        }
    }

    fun pause() {
        try {
            player?.apply {
                if (isPlaying) {
                    pause()
                }
            }
        } catch (e: Exception) {
        }
    }

    fun stop() {
        try {
            player?.stop()
        } catch (e: Exception) {
        }
    }

    fun destroy() {
        try {
            player?.release()
            player = null
        } catch (e: Exception) {
        }
    }
}