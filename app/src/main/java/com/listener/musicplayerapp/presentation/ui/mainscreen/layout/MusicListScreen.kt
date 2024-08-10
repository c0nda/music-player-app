package com.listener.musicplayerapp.presentation.ui.mainscreen.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenEvent
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenUIState


@Composable
fun MainListScreen(
    onEvent: (HomeScreenEvent) -> Unit,
    uiState: HomeScreenUIState
) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))

        with(uiState) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(100.dp)
                                .fillMaxHeight()
                                .align(Alignment.Center)
                                .padding(16.dp)
                        )
                    }
                }

                !isLoading && error == null -> {
                    if (songs != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                                items(items = songs) { song ->
                                    SongContent(
                                        song = song,
                                        onClickCard = {
                                            onEvent(HomeScreenEvent.OnSongSelected(song))
                                            onEvent(HomeScreenEvent.Play)
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    CurrentMusicBottomSheet()
}