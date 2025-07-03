package com.listener.musicplayerapp.presentation.navigation

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("home")
}