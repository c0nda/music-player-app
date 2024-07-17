package com.listener.musicplayerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.listener.musicplayerapp.presentation.ui.mainscreen.layout.MainListScreen
import com.listener.musicplayerapp.presentation.ui.theme.MusicplayerappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicplayerappTheme {
                MainListScreen()
            }
        }
    }
}
