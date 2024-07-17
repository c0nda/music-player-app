package com.listener.musicplayerapp.di

import com.listener.musicplayerapp.domain.repository.SongRepository
import com.listener.musicplayerapp.domain.service.PlayerController
import com.listener.musicplayerapp.domain.usecase.AddMediaItemsUseCase
import com.listener.musicplayerapp.domain.usecase.DestroyPlayerControllerUseCase
import com.listener.musicplayerapp.domain.usecase.GetAllSongsUseCase
import com.listener.musicplayerapp.domain.usecase.GetCurrentPositionUseCase
import com.listener.musicplayerapp.domain.usecase.GetCurrentSongUseCase
import com.listener.musicplayerapp.domain.usecase.GoToNextSongUseCase
import com.listener.musicplayerapp.domain.usecase.GoToPreviousSongUseCase
import com.listener.musicplayerapp.domain.usecase.PauseMusicUseCase
import com.listener.musicplayerapp.domain.usecase.PlayMusicUseCase
import com.listener.musicplayerapp.domain.usecase.ResumeMusicUseCase
import com.listener.musicplayerapp.domain.usecase.SeekToPositionUseCase
import com.listener.musicplayerapp.domain.usecase.SetPlayerControllerCallbackUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideAddMediaItemsUseCase(playerController: PlayerController) =
        AddMediaItemsUseCase(playerController)

    @Provides
    fun provideDestroyPlayerControllerUseCase(playerController: PlayerController) =
        DestroyPlayerControllerUseCase(playerController)

    @Provides
    fun provideGetAllSongsUseCase(songRepository: SongRepository) =
        GetAllSongsUseCase(songRepository)

    @Provides
    fun provideGetCurrentPositionUseCase(playerController: PlayerController) =
        GetCurrentPositionUseCase(playerController)

    @Provides
    fun provideGetCurrentSongUseCase(playerController: PlayerController) =
        GetCurrentSongUseCase(playerController)

    @Provides
    fun provideGoToNextSongUseCase(playerController: PlayerController) =
        GoToNextSongUseCase(playerController)

    @Provides
    fun provideGoToPreviousSongUseCase(playerController: PlayerController) =
        GoToPreviousSongUseCase(playerController)

    @Provides
    fun providePauseMusicUseCase(playerController: PlayerController) =
        PauseMusicUseCase(playerController)

    @Provides
    fun providePlayMusicUseCase(playerController: PlayerController) =
        PlayMusicUseCase(playerController)

    @Provides
    fun provideResumeMusicUseCase(playerController: PlayerController) =
        ResumeMusicUseCase(playerController)

    @Provides
    fun provideSeekToPositionUseCase(playerController: PlayerController) =
        SeekToPositionUseCase(playerController)

    @Provides
    fun provideSetPlayerControllerCallback(playerController: PlayerController) =
        SetPlayerControllerCallbackUseCase(playerController)
}
