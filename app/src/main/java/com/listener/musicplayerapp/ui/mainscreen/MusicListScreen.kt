package com.listener.musicplayerapp.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private fun songs(): List<String> {
    return List(100) { "Song $it" }
}

@Composable
fun MainListScreen(
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        SearchBar()
        SongList(songs = songs())
    }
}

@Preview(showBackground = true)
@Composable
private fun MainListScreenPreview() {
    MainListScreen()
}