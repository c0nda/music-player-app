package com.listener.musicplayerapp.utils

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.presentation.ui.common.PlayerState


fun MediaItem.toSong(): Song {
    return Song(
        id = mediaId,
        songName = mediaMetadata.title.toString(),
        author = mediaMetadata.artist.toString(),
        uri = localConfiguration?.uri!!
    )
}

fun Int.toPlayerState(isPlaying: Boolean): PlayerState {
    return when (this) {
        Player.STATE_IDLE -> PlayerState.STOPPED
        Player.STATE_ENDED -> PlayerState.STOPPED
        else -> if (isPlaying) PlayerState.PLAYING else PlayerState.PAUSED
    }
}