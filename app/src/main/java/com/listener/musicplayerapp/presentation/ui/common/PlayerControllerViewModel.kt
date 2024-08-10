package com.listener.musicplayerapp.presentation.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listener.musicplayerapp.domain.usecase.DestroyPlayerControllerUseCase
import com.listener.musicplayerapp.domain.usecase.GetCurrentPositionUseCase
import com.listener.musicplayerapp.domain.usecase.SetPlayerControllerCallbackUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerControllerViewModel @Inject constructor(
    private val getCurrentPositionUseCase: GetCurrentPositionUseCase,
    private val setPlayerControllerCallbackUseCase: SetPlayerControllerCallbackUseCase,
    private val destroyPlayerControllerUseCase: DestroyPlayerControllerUseCase
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
                        _playerControllerUIState.value = _playerControllerUIState.value.copy(
                            currentPosition = getCurrentPositionUseCase.execute()
                        )
                    }
                }
            }
        }
    }

    fun destroyPlayerController() {
        destroyPlayerControllerUseCase.execute()
    }
}