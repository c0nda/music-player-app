package com.listener.musicplayerapp.presentation.ui.mainscreen.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.listener.musicplayerapp.R
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.presentation.ui.common.PlayerState
import com.listener.musicplayerapp.presentation.ui.mainscreen.HomeScreenEvent
import com.listener.musicplayerapp.presentation.ui.playerscreen.layout.PlayerScreen

@Composable
fun CurrentMusicBottomSheet(
    modifier: Modifier = Modifier,
    onEvent: (HomeScreenEvent) -> Unit,
    playerState: PlayerState?,
    song: Song?,
    onBarClick: () -> Unit
) {
    AnimatedVisibility(
        visible = playerState != PlayerState.STOPPED,
        modifier = modifier
    ) {
        if (song != null) {
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
                            Text(text = song.songName)
                            Text(text = song.author)
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
//                    PlayerScreen()
                }
            }
        }
    }
}