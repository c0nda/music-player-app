package com.listener.musicplayerapp.di.modules

import com.listener.musicplayerapp.data.repository.SongRepositoryImpl
import com.listener.musicplayerapp.domain.repository.SongRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSongRepository(songRepositoryImpl: SongRepositoryImpl): SongRepository

}