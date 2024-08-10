package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.service.PlayerController
import com.listener.musicplayerapp.presentation.ui.common.PlayerState
import javax.inject.Inject

class SetPlayerControllerCallbackUseCase @Inject constructor(private val playerController: PlayerController) {

    fun execute(
        callback: (
            playerState: PlayerState,
            currentSong: Song?,
            currentPosition: Long,
            duration: Long,
            isRepeatOneEnabled: Boolean,
            isShuffledEnabled: Boolean
        ) -> Unit
    ) {
        playerController.playerControllerCallback = callback
    }
}