package com.listener.musicplayerapp.di

import com.listener.musicplayerapp.data.repository.SongRepositoryImpl
import com.listener.musicplayerapp.domain.repository.SongRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindSongRepository(songRepositoryImpl: SongRepositoryImpl): SongRepository

}