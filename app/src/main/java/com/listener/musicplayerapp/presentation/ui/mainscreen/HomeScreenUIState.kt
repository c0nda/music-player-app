package com.listener.musicplayerapp.presentation.ui.mainscreen

import com.listener.musicplayerapp.domain.model.Song

data class HomeScreenUIState(
    val isLoading: Boolean = false,
    val songs: List<Song?> = emptyList(),
    val currentSong: Song? = null,
    val error: String? = null
)