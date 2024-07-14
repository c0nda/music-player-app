package com.listener.musicplayerapp.di

import com.listener.musicplayerapp.data.local.LocalDataSource
import com.listener.musicplayerapp.data.local.device.DeviceStorage
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindLocalDataSource(deviceStorage: DeviceStorage): LocalDataSource
}