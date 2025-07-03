package com.listener.musicplayerapp.presentation.mainscreen

import com.listener.musicplayerapp.domain.model.Song

data class MainScreenUIState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val currentSong: Song? = null,
    val error: String? = null
)