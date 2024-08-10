package com.listener.musicplayerapp.presentation.ui.common

import com.listener.musicplayerapp.domain.model.Song

data class PlayerControllerUIState(
    val playerState: PlayerState? = null,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isShuffledEnabled: Boolean = false,
    val isRepeatOneEnabled: Boolean = false
)