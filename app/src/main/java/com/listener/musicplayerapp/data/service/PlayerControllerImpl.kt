package com.listener.musicplayerapp.data.service

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.service.PlayerController
import com.listener.musicplayerapp.utils.PlayerState
import com.listener.musicplayerapp.utils.toSong

class PlayerControllerImpl(context: Context) : PlayerController {

    private var factory: ListenableFuture<MediaController>
    private var mediaController: MediaController? = null

    init {
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        factory = MediaController.Builder(
            context,
            sessionToken
        ).buildAsync()
        factory.addListener(
            {
                mediaController = factory.let {
                    if (it.isDone) {
                        it.get()
                    } else {
                        null
                    }
                }
            },
            MoreExecutors.directExecutor()
        )
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
                .setMediaId(it.id.toString())
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

    override fun play(mediaItemId: Int) {
        mediaController?.apply {
            seekToDefaultPosition(mediaItemId)
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

    override fun destroy() {
        MediaController.releaseFuture(factory)
        playerControllerCallback = null
    }
}