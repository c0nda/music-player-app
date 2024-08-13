package com.listener.musicplayerapp.di

import android.content.Context
import com.listener.musicplayerapp.data.service.MusicService
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
        HomeScreenModule::class,
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