package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.service.PlayerController
import com.listener.musicplayerapp.utils.PlayerState
import javax.inject.Inject

class SetPlayerControllerCallbackUseCase @Inject constructor(private val playerController: PlayerController) {

    fun execute(
        callback: (
            playerState: PlayerState,
            currentSong: Song?,
            currentPosition: Long,
            duration: Long,
            isRepeatOneEnabled: Boolean,
            isShuffleAllEnabled: Boolean
        ) -> Unit
    ) {
        playerController.playerControllerCallback = callback
    }
}