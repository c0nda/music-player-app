package com.listener.musicplayerapp.presentation.ui.mainscreen.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.listener.musicplayerapp.R
import com.listener.musicplayerapp.presentation.ui.playerscreen.layout.PlayerScreen

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
    CurrentMusicBottomSheet()
}

@Composable
fun CurrentMusicBottomSheet(
    modifier: Modifier = Modifier
) {
    var progress by rememberSaveable { mutableStateOf(0f) }
    val context = LocalContext.current
    val scene = remember {
        context
            .resources
            .openRawResource(R.raw.current_music_motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = scene),
        progress = progress,
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .layoutId("music_bottom_sheet")
        ) {
            Row {
                Image(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
                Column {
                    Text(text = "Song Name")
                    Text(text = "Author")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .layoutId("music_player")
        ) {
            PlayerScreen()
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun MainListScreenPreview() {
    MainListScreen()
}