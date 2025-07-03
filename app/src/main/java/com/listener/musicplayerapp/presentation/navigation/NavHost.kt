package com.listener.musicplayerapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.listener.musicplayerapp.DI
import com.listener.musicplayerapp.presentation.common.PlayerControllerViewModel
import com.listener.musicplayerapp.presentation.common.daggerViewModel
import com.listener.musicplayerapp.presentation.bottomsheet.layout.CurrentMusicBottomSheet
import com.listener.musicplayerapp.presentation.common.PlayerEvent
import com.listener.musicplayerapp.presentation.mainscreen.layout.MusicListScreen
import com.listener.musicplayerapp.presentation.playerscreen.PlayerViewModel

@Composable
fun PlayerNavHost(
    navController: NavHostController,
    playerControllerViewModel: PlayerControllerViewModel
) {
    val playerControllerUIState =
        playerControllerViewModel.playerControllerUIState.collectAsState().value

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            val playerViewModel: PlayerViewModel = daggerViewModel {
                DI.appComponent.viewModelFactory().create(PlayerViewModel::class.java)
            }
            var isInitialized by rememberSaveable { mutableStateOf(false) }

            if (!isInitialized) {
                LaunchedEffect(key1 = Unit) {
                    playerViewModel.onEvent(PlayerEvent.GetSong)
                    isInitialized = true
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                MusicListScreen(
                    onEvent = playerViewModel::onEvent,
                    uiState = playerViewModel.playerScreenUiState.collectAsState().value
                )
                CurrentMusicBottomSheet(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onEvent = playerViewModel::onEvent,
                    playerControllerUIState = playerControllerUIState,
                    onBarClick = {  }
                )
            }
        }
    }
}