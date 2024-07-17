package com.listener.musicplayerapp.utils

import androidx.media3.common.MediaItem
import com.listener.musicplayerapp.domain.model.Song


fun MediaItem.toSong(): Song {
    return Song(
        id = mediaId.toInt(),
        songName = mediaMetadata.title.toString(),
        author = mediaMetadata.artist.toString(),
        duration = 0
    )
}