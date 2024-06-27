package com.listener.musicplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.listener.musicplayerapp.ui.mainscreen.MainListScreen
import com.listener.musicplayerapp.ui.theme.MusicplayerappTheme

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
