package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.service.PlayerController
import javax.inject.Inject

class GetCurrentPositionUseCase @Inject constructor(private val playerController: PlayerController) {

    fun execute(): Long = playerController.getCurPos()
}