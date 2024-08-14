package com.listener.musicplayerapp.presentation.ui.playerscreen

import androidx.lifecycle.ViewModel
import com.listener.musicplayerapp.domain.usecase.GoToNextSongUseCase
import com.listener.musicplayerapp.domain.usecase.GoToPreviousSongUseCase
import com.listener.musicplayerapp.domain.usecase.PauseMusicUseCase
import com.listener.musicplayerapp.domain.usecase.ResumeMusicUseCase
import com.listener.musicplayerapp.domain.usecase.SeekToPositionUseCase
import javax.inject.Inject

class PlayerScreenViewModel @Inject constructor(
    private val pauseMusicUseCase: PauseMusicUseCase,
    private val resumeMusicUseCase: ResumeMusicUseCase,
    private val goToNextSongUseCase: GoToNextSongUseCase,
    private val goToPreviousSongUseCase: GoToPreviousSongUseCase,
    private val seekToPositionUseCase: SeekToPositionUseCase
) : ViewModel() {

    fun onEvent(event: PlayerEvent) {
        when (event) {
            PlayerEvent.PauseSong -> pause()
            PlayerEvent.ResumeSong -> resume()
            PlayerEvent.GoToNextSong -> goToNext()
            PlayerEvent.GoToPrevSong -> goToPrev()
            is PlayerEvent.SeekToPosition -> seekToPosition(event.position)
        }
    }

    private fun pause() {
        pauseMusicUseCase.execute()
    }

    private fun resume() {
        resumeMusicUseCase.execute()
    }

    private fun goToNext() {
        goToNextSongUseCase.execute()
    }

    private fun goToPrev() {
        goToPreviousSongUseCase.execute()
    }

    private fun seekToPosition(position: Long) {
        seekToPositionUseCase.execute(position)
    }
}