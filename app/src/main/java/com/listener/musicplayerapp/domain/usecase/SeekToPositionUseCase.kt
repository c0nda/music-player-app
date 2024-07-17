package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.service.PlayerController
import javax.inject.Inject

class SeekToPositionUseCase @Inject constructor(private val playerController: PlayerController) {

    fun execute(position: Long) {
        playerController.seekToPos(position)
    }
}