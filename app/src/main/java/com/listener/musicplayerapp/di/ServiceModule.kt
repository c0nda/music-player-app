package com.listener.musicplayerapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import com.listener.musicplayerapp.data.service.PlayerControllerImpl
import com.listener.musicplayerapp.domain.service.PlayerController
import dagger.Binds
import javax.inject.Singleton

@Module
abstract class ServiceModule {

    companion object {
        @Provides
        fun provideAudioAttributes() = AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

        @Provides
        fun provideExoPlayer(
            context: Context,
            audioAttributes: AudioAttributes
        ) = ExoPlayer.Builder(context).build().apply {
            setAudioAttributes(audioAttributes, true)
            setHandleAudioBecomingNoisy(true)
        }
    }

    @Singleton
    @Binds
    abstract fun bindPlayerController(playerControllerImpl: PlayerControllerImpl): PlayerController
}