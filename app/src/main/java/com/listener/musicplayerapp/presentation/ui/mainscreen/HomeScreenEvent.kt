package com.listener.musicplayerapp.presentation.ui.mainscreen

import com.listener.musicplayerapp.domain.model.Song

sealed class HomeScreenEvent {
    data object Play : HomeScreenEvent()
    data object Resume : HomeScreenEvent()
    data object PauseSong : HomeScreenEvent()
    data object GoToNext : HomeScreenEvent()
    data object GoToPrev : HomeScreenEvent()
    data object GetSong : HomeScreenEvent()
    data class OnSongSelected(val song: Song) : HomeScreenEvent()
}