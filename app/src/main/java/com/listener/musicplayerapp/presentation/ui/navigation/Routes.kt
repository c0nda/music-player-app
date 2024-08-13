package com.listener.musicplayerapp.presentation.ui.navigation

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("home")
    data object SongScreen : Routes("song")
}