package com.listener.musicplayerapp.di

import androidx.lifecycle.ViewModel
import com.listener.musicplayerapp.di.viewmodel.ViewModelKey
import com.listener.musicplayerapp.presentation.ui.common.PlayerControllerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SharedModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlayerControllerViewModel::class)
    abstract fun playerControllerViewModel(playerControllerViewModel: PlayerControllerViewModel): ViewModel
}