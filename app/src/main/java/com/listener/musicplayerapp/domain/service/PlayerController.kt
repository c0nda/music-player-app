package com.listener.musicplayerapp.domain.service

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.utils.PlayerState

interface PlayerController {

    var playerControllerCallback: (
        (
        playerState: PlayerState,
        currentSong: Song?,
        currentPosition: Long,
        duration: Long,
        isRepeatOneEnabled: Boolean,
        isShuffleAllEnabled: Boolean
    ) -> Unit)?

    fun addMediaItems(songs: List<Song>)

    fun play(mediaItemId: Int)

    fun pause()

    fun resume()

    fun getCurPos(): Long

    fun getCurSong(): Song?

    fun goToNextSong()

    fun goToPrevSong()

    fun seekToPos(position: Long)

    fun destroy()
}