package com.listener.musicplayerapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.listener.musicplayerapp.data.service.MusicService
import com.listener.musicplayerapp.presentation.ui.common.PlayerControllerViewModel
import com.listener.musicplayerapp.presentation.ui.common.layout.MusicPlayerApp
import com.listener.musicplayerapp.presentation.ui.theme.MusicplayerappTheme

class MainActivity : ComponentActivity() {

    private val playerControllerViewModel: PlayerControllerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicplayerappTheme {
                MusicPlayerApp(
                    playerControllerViewModel = playerControllerViewModel
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerControllerViewModel.destroyPlayerController()
        stopService(Intent(this, MusicService::class.java))
    }
}
