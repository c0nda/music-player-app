package com.listener.musicplayerapp.di.modules

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.listener.musicplayerapp.data.service.MediaSessionCallback
import com.listener.musicplayerapp.data.service.MusicService
import com.listener.musicplayerapp.data.service.PlayerControllerImpl
import com.listener.musicplayerapp.domain.service.PlayerController
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ServiceModule {
    companion object {

        @Singleton
        @Provides
        fun provideAudioAttributes() = AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

        @Singleton
        @Provides
        fun provideExoPlayer(
            context: Context,
            audioAttributes: AudioAttributes
        ) = ExoPlayer.Builder(context).build().apply {
            setAudioAttributes(audioAttributes, true)
            setHandleAudioBecomingNoisy(true)
        }

        @Singleton
        @Provides
        fun providePlayerControllerFuture(context: Context): ListenableFuture<MediaController> {
            val sessionToken =
                SessionToken(context, ComponentName(context, MusicService::class.java))
            return MediaController.Builder(context, sessionToken).buildAsync()
        }

        @Singleton
        @Provides
        fun providePlayerController(factory: ListenableFuture<MediaController>): PlayerControllerImpl {
            return PlayerControllerImpl(factory)
        }

        @Singleton
        @Provides
        fun provideMediaSession(
            context: Context,
            exoPlayer: ExoPlayer,
            mediaSessionCallback: MediaSession.Callback
        ): MediaSession {
            return MediaSession.Builder(context, exoPlayer)
                .setCallback(mediaSessionCallback)
                .build()
        }

        @Singleton
        @Provides
        fun provideMediaSessionCallback(): MediaSession.Callback {
            return MediaSessionCallback()
        }
    }

    @Singleton
    @Binds
    abstract fun bindPlayerController(playerControllerImpl: PlayerControllerImpl): PlayerController
}