package com.listener.musicplayerapp.presentation.common

import com.listener.musicplayerapp.domain.model.Song

sealed class PlayerEvent {
    data object Play : PlayerEvent()
    data object Resume : PlayerEvent()
    data object PauseSong : PlayerEvent()
    data object GoToNextSong : PlayerEvent()
    data object GoToPrevSong : PlayerEvent()
    data object GetSong : PlayerEvent()
    data class OnSongSelected(val song: Song) : PlayerEvent()
    data class SeekToPosition(val position: Long) : PlayerEvent()
}