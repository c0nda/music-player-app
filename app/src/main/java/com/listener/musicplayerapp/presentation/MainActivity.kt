package com.listener.musicplayerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.listener.musicplayerapp.DI
import com.listener.musicplayerapp.presentation.common.PlayerControllerViewModel
import com.listener.musicplayerapp.presentation.navigation.PlayerNavHost
import com.listener.musicplayerapp.presentation.ui.theme.MusicplayerappTheme

class MainActivity : ComponentActivity() {

    private val component by lazy { DI.appComponent }

    private val playerControllerViewModel by viewModels<PlayerControllerViewModel> {
        component.viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicplayerappTheme {
                PlayerNavHost(
                    navController = rememberNavController(),
                    playerControllerViewModel = playerControllerViewModel
                )
            }
        }
    }
}
