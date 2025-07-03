package com.listener.musicplayerapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listener.musicplayerapp.domain.usecase.GetCurrentPositionUseCase
import com.listener.musicplayerapp.domain.usecase.SetPlayerControllerCallbackUseCase
import com.listener.musicplayerapp.presentation.playerscreen.PlayerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerControllerViewModel @Inject constructor(
    private val getCurrentPositionUseCase: GetCurrentPositionUseCase,
    private val setPlayerControllerCallbackUseCase: SetPlayerControllerCallbackUseCase
) : ViewModel() {

    private var _playerControllerUIState = MutableStateFlow(PlayerControllerUIState())
    val playerControllerUIState = _playerControllerUIState.asStateFlow()

    init {
        setPlayerControllerCallback()
    }

    private fun setPlayerControllerCallback() {
        setPlayerControllerCallbackUseCase.execute { playerState,
                                                     currentSong,
                                                     currentPosition,
                                                     duration,
                                                     isRepeatOneEnabled,
                                                     isShuffledEnabled ->
            _playerControllerUIState.value = _playerControllerUIState.value.copy(
                playerState = playerState,
                currentSong = currentSong,
                currentPosition = currentPosition,
                duration = duration,
                isShuffledEnabled = isShuffledEnabled,
                isRepeatOneEnabled = isRepeatOneEnabled
            )

            if (playerState == PlayerState.PLAYING) {
                viewModelScope.launch {
                    while (true) {
                        delay(3000)
                        _playerControllerUIState.value = _playerControllerUIState.value.copy(
                            currentPosition = getCurrentPositionUseCase.execute()
                        )
                    }
                }
            }
        }
    }
}