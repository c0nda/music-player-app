package com.listener.musicplayerapp.di

import android.content.Context
import com.listener.musicplayerapp.data.service.MusicService
import com.listener.musicplayerapp.di.modules.DataModule
import com.listener.musicplayerapp.di.modules.RepositoryModule
import com.listener.musicplayerapp.di.modules.ServiceModule
import com.listener.musicplayerapp.di.modules.SharedModule
import com.listener.musicplayerapp.di.modules.UseCaseModule
import com.listener.musicplayerapp.di.viewmodel.BaseViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ServiceModule::class,
        RepositoryModule::class,
        DataModule::class,
        UseCaseModule::class,
        SharedModule::class
    ]
)
@Singleton
interface AppComponent {

    fun viewModelFactory(): BaseViewModelFactory

    fun inject(musicService: MusicService)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}