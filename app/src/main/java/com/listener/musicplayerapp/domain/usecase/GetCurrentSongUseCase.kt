package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.service.PlayerController
import javax.inject.Inject

class GetCurrentSongUseCase @Inject constructor(private val playerController: PlayerController) {

    fun execute(): Song? = playerController.getCurSong()
}