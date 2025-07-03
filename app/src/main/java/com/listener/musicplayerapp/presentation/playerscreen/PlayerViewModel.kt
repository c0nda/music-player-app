package com.listener.musicplayerapp.presentation.playerscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listener.musicplayerapp.domain.usecase.AddMediaItemsUseCase
import com.listener.musicplayerapp.domain.usecase.GetAllSongsUseCase
import com.listener.musicplayerapp.domain.usecase.GoToNextSongUseCase
import com.listener.musicplayerapp.domain.usecase.GoToPreviousSongUseCase
import com.listener.musicplayerapp.domain.usecase.PauseMusicUseCase
import com.listener.musicplayerapp.domain.usecase.PlayMusicUseCase
import com.listener.musicplayerapp.domain.usecase.ResumeMusicUseCase
import com.listener.musicplayerapp.presentation.common.PlayerEvent
import com.listener.musicplayerapp.presentation.mainscreen.MainScreenUIState
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val addMediaItemsUseCase: AddMediaItemsUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val goToNextSongUseCase: GoToNextSongUseCase,
    private val goToPreviousSongUseCase: GoToPreviousSongUseCase,
    private val playMusicUseCase: PlayMusicUseCase,
    private val resumeMusicUseCase: ResumeMusicUseCase,
    private val pauseMusicUseCase: PauseMusicUseCase
) : ViewModel() {

    private val _playerScreenUiState = MutableStateFlow(MainScreenUIState())
    val playerScreenUiState = _playerScreenUiState.asStateFlow()

    fun onEvent(event: PlayerEvent) {
        when (event) {
            PlayerEvent.Play -> play()
            PlayerEvent.Resume -> resume()
            PlayerEvent.PauseSong -> pause()
            PlayerEvent.GetSong -> getCurrentSong()
            PlayerEvent.GoToNextSong -> goToNext()
            PlayerEvent.GoToPrevSong -> goToPrev()
            is PlayerEvent.OnSongSelected -> _playerScreenUiState.value =
                _playerScreenUiState.value.copy(
                    currentSong = event.song
                )

            is PlayerEvent.SeekToPosition -> TODO()
        }
    }


    private fun play() {
        _playerScreenUiState.value.apply {
            songs?.indexOf(currentSong).let { mediaItemId ->
                if (mediaItemId != null) {
                    playMusicUseCase.execute(mediaItemId)
                }
            }
        }
    }

    private fun getCurrentSong() {
        _playerScreenUiState.value = _playerScreenUiState.value.copy(isLoading = true)

        viewModelScope.launch {
            getAllSongsUseCase.execute().catch {
                _playerScreenUiState.value = _playerScreenUiState.value.copy(
                    isLoading = false,
                    error = it.message
                )
            }.collect {
                _playerScreenUiState.value = when (it) {
                    is Result.Success -> {

                        it.data.let { songs ->
                            delay(1000)
                            addMediaItemsUseCase.execute(songs)
                        }

                        _playerScreenUiState.value.copy(
                            isLoading = false,
                            songs = it.data
                        )
                    }

                    is Result.Loading -> {
                        _playerScreenUiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        _playerScreenUiState.value.copy(
                            isLoading = false,
                            error = it.error?.message
                        )
                    }
                }
            }
        }
    }

    private fun pause() = pauseMusicUseCase.execute()

    private fun resume() = resumeMusicUseCase.execute()

    private fun goToNext() = goToNextSongUseCase.execute()

    private fun goToPrev() = goToPreviousSongUseCase.execute()
}