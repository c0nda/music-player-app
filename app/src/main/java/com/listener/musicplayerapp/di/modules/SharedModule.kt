package com.listener.musicplayerapp.di.modules

import androidx.lifecycle.ViewModel
import com.listener.musicplayerapp.di.viewmodel.ViewModelKey
import com.listener.musicplayerapp.presentation.common.PlayerControllerViewModel
import com.listener.musicplayerapp.presentation.playerscreen.PlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SharedModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlayerControllerViewModel::class)
    abstract fun playerControllerViewModel(playerControllerViewModel: PlayerControllerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun playerViewModel(viewModel: PlayerViewModel): ViewModel
}