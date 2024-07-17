package com.listener.musicplayerapp.presentation.ui.mainscreen.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.listener.musicplayerapp.presentation.ui.mainscreen.layout.SongContent

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<String>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = songs) { song ->
            SongContent(songName = song)
        }
    }
}