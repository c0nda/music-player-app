package com.listener.musicplayerapp.data.service

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.listener.musicplayerapp.DI
import javax.inject.Inject

class MusicService : MediaSessionService() {

    @Inject
    lateinit var mediaSession: MediaSession

    @Inject
    lateinit var exoPlayer: ExoPlayer

    override fun onCreate() {
        super.onCreate()

        DI.appComponent.inject(this)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession.run {
            exoPlayer.release()
            release()
        }
        super.onDestroy()
    }
}