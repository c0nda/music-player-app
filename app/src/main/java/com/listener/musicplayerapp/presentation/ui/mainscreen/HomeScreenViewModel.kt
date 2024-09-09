package com.listener.musicplayerapp.presentation.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listener.musicplayerapp.domain.usecase.AddMediaItemsUseCase
import com.listener.musicplayerapp.domain.usecase.GetAllSongsUseCase
import com.listener.musicplayerapp.domain.usecase.GoToNextSongUseCase
import com.listener.musicplayerapp.domain.usecase.GoToPreviousSongUseCase
import com.listener.musicplayerapp.domain.usecase.PauseMusicUseCase
import com.listener.musicplayerapp.domain.usecase.PlayMusicUseCase
import com.listener.musicplayerapp.domain.usecase.ResumeMusicUseCase
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val addMediaItemsUseCase: AddMediaItemsUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val goToNextSongUseCase: GoToNextSongUseCase,
    private val goToPreviousSongUseCase: GoToPreviousSongUseCase,
    private val playMusicUseCase: PlayMusicUseCase,
    private val resumeMusicUseCase: ResumeMusicUseCase,
    private val pauseMusicUseCase: PauseMusicUseCase
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUIState())
    val homeScreenUIState = _homeScreenUiState.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.Play -> play()
            HomeScreenEvent.Resume -> resume()
            HomeScreenEvent.PauseSong -> pause()
            HomeScreenEvent.GetSong -> getCurrentSong()
            HomeScreenEvent.GoToNext -> goToNext()
            HomeScreenEvent.GoToPrev -> goToPrev()
            is HomeScreenEvent.OnSongSelected -> _homeScreenUiState.value =
                _homeScreenUiState.value.copy(
                    currentSong = event.song
                )
        }
    }


    private fun play() {
        _homeScreenUiState.value.apply {
            songs?.indexOf(currentSong).let { mediaItemId ->
                if (mediaItemId != null) {
                    playMusicUseCase.execute(mediaItemId)
                }
            }
        }
    }

    private fun getCurrentSong() {
        _homeScreenUiState.value = _homeScreenUiState.value.copy(isLoading = true)

        viewModelScope.launch {
            getAllSongsUseCase.execute().catch {
                _homeScreenUiState.value = _homeScreenUiState.value.copy(
                    isLoading = false,
                    error = it.message
                )
            }.collect {
                _homeScreenUiState.value = when (it) {
                    is Result.Success -> {

                        it.data.let { songs ->
                            delay(1000)
                            addMediaItemsUseCase.execute(songs)
                        }

                        _homeScreenUiState.value.copy(
                            isLoading = false,
                            songs = it.data
                        )
                    }

                    is Result.Loading -> {
                        _homeScreenUiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        _homeScreenUiState.value.copy(
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