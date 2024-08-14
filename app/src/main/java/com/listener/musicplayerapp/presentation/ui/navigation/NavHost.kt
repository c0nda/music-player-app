package com.listener.musicplayerapp.presentation.ui.navigation

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
import com.listener.musicplayerapp.presentation.ui.common.PlayerControllerViewModel
import com.listener.musicplayerapp.presentation.ui.common.daggerViewModel
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenEvent
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenViewModel
import com.listener.musicplayerapp.presentation.ui.mainscreen.layout.CurrentMusicBottomSheet
import com.listener.musicplayerapp.presentation.ui.mainscreen.layout.MusicListScreen
import com.listener.musicplayerapp.presentation.ui.playerscreen.PlayerScreenViewModel
import com.listener.musicplayerapp.presentation.ui.playerscreen.layout.PlayerScreen

@Composable
fun PlayerNavHost(
    navController: NavHostController,
    playerControllerViewModel: PlayerControllerViewModel
) {
    val playerControllerUIState =
        playerControllerViewModel.playerControllerUIState.collectAsState().value

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            val homeScreenViewModel: HomeScreenViewModel = daggerViewModel {
                DI.appComponent.viewModelFactory().create(HomeScreenViewModel::class.java)
            }
            var isInitialized by rememberSaveable { mutableStateOf(false) }

            if (!isInitialized) {
                LaunchedEffect(key1 = Unit) {
                    homeScreenViewModel.onEvent(HomeScreenEvent.GetSong)
                    isInitialized = true
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                MusicListScreen(
                    onEvent = homeScreenViewModel::onEvent,
                    uiState = homeScreenViewModel.homeScreenUIState.collectAsState().value
                )
                CurrentMusicBottomSheet(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onEvent = homeScreenViewModel::onEvent,
                    song = playerControllerUIState.currentSong,
                    playerState = playerControllerUIState.playerState,
                    onBarClick = { navController.navigate(Routes.SongScreen.route) }
                )
            }
        }

        composable(route = Routes.SongScreen.route) {
            val playerScreenViewModel: PlayerScreenViewModel = daggerViewModel {
                DI.appComponent.viewModelFactory().create(PlayerScreenViewModel::class.java)
            }
            PlayerScreen(
                playerControllerUIState = playerControllerUIState,
                onEvent = playerScreenViewModel::onEvent
            )
        }
    }
}