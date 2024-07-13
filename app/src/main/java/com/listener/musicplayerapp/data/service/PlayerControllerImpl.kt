package com.listener.musicplayerapp.data.service

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.listener.musicplayerapp.domain.service.PlayerController

class PlayerControllerImpl(context: Context): PlayerController {

    private var factory: ListenableFuture<MediaController>? = null
    private var mediaController: MediaController? = null

    init {
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        factory = MediaController.Builder(
            context,
            sessionToken
        ).buildAsync()
        factory?.addListener(
            {
                mediaController = factory?.let {
                    if (it.isDone) {
                        it.get()
                    } else {
                        null
                    }
                }
            },
            MoreExecutors.directExecutor()
        )
    }
}