package com.listener.musicplayerapp.data.service

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.service.PlayerController
import com.listener.musicplayerapp.presentation.ui.common.PlayerState
import com.listener.musicplayerapp.utils.toPlayerState
import com.listener.musicplayerapp.utils.toSong
import javax.inject.Inject

class PlayerControllerImpl @Inject constructor(
    private val factory: ListenableFuture<MediaController>
) : PlayerController {

    private val mediaController: MediaController?
        get() = if (factory.isDone) factory.get() else null

    init {
        factory.addListener({
            controllerListener()
        }, MoreExecutors.directExecutor())
    }

    override var playerControllerCallback: (
        (
        playerState: PlayerState,
        currentSong: Song?,
        currentPosition: Long,
        duration: Long,
        isRepeatOneEnabled: Boolean,
        isShuffleAllEnabled: Boolean
    ) -> Unit)? = null

    override fun addMediaItems(songs: List<Song>) {
        val mediaItems = songs.map {
            MediaItem.Builder()
                .setUri(it.uri)
                .setMediaId(it.uri.toString())
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(it.songName)
                        .setArtist(it.author)
                        .build()
                )
                .build()
        }
        mediaController?.setMediaItems(mediaItems)
    }

    override fun play(mediaItemIndex: Int) {
        mediaController?.apply {
            seekToDefaultPosition(mediaItemIndex)
            playWhenReady = true
            prepare()
        }
    }

    override fun pause() {
        mediaController?.pause()
    }

    override fun resume() {
        mediaController?.play()
    }

    override fun getCurPos(): Long {
        return mediaController?.currentPosition ?: 0L
    }

    override fun getCurSong(): Song? {
        return mediaController?.currentMediaItem?.toSong()
    }

    override fun goToNextSong() {
        mediaController?.seekToNext()
    }

    override fun goToPrevSong() {
        mediaController?.seekBack()
    }

    override fun seekToPos(position: Long) {
        mediaController?.seekTo(position)
    }

    private fun controllerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                with(player) {
                    if (currentMediaItem != null) {
                        playerControllerCallback?.invoke(
                            playbackState.toPlayerState(isPlaying),
                            currentMediaItem!!.toSong(),
                            currentPosition.coerceAtLeast(0L),
                            duration.coerceAtLeast(0L),
                            repeatMode == Player.REPEAT_MODE_ONE,
                            shuffleModeEnabled,
                        )
                    }
                }
            }
        })
    }
}