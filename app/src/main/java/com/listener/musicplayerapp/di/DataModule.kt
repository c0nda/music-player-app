package com.listener.musicplayerapp.di

import com.listener.musicplayerapp.data.local.LocalDataSource
import com.listener.musicplayerapp.data.local.device.DeviceStorage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(deviceStorage: DeviceStorage): LocalDataSource
}