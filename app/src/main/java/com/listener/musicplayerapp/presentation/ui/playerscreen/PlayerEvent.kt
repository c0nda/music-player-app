package com.listener.musicplayerapp.presentation.ui.playerscreen

sealed class PlayerEvent {
    data object PauseSong : PlayerEvent()
    data object ResumeSong : PlayerEvent()
    data object GoToNextSong : PlayerEvent()
    data object GoToPrevSong : PlayerEvent()
    data class SeekToPosition(val position: Long) : PlayerEvent()
}