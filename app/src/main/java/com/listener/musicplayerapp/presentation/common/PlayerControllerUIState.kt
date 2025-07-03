package com.listener.musicplayerapp.presentation.common

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.presentation.playerscreen.PlayerState

data class PlayerControllerUIState(
    val playerState: PlayerState? = null,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isShuffledEnabled: Boolean = false,
    val isRepeatOneEnabled: Boolean = false
)