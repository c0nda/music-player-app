package com.listener.musicplayerapp.di

import androidx.lifecycle.ViewModel
import com.listener.musicplayerapp.di.viewmodel.ViewModelKey
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    abstract fun homeScreenViewModel(viewModel: HomeScreenViewModel): ViewModel
}