package com.listener.musicplayerapp.presentation.bottomsheet.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.listener.musicplayerapp.R
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.presentation.common.PlayerControllerUIState
import com.listener.musicplayerapp.presentation.common.PlayerEvent
import com.listener.musicplayerapp.presentation.playerscreen.layout.PlayerScreen
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalWearMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun CurrentMusicBottomSheet(
    modifier: Modifier = Modifier,
    onEvent: (PlayerEvent) -> Unit,
    playerControllerUIState: PlayerControllerUIState,
    onBarClick: () -> Unit
) {
    if (playerControllerUIState.currentSong != null) {
        val context = LocalContext.current
        val density = LocalDensity.current
        val scope = rememberCoroutineScope()
        val configuration = LocalConfiguration.current

        val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
        val swipeAreaHeight = screenHeight - 400

        val motionSceneContent = remember {
            context.resources
                .openRawResource(R.raw.current_music_motion_scene)
                .readBytes()
                .decodeToString()
        }

        val swipeableState = rememberSwipeableState(0)
        val anchors = mapOf(0f to 0, -swipeAreaHeight to 1)
        val swipeProgress = swipeableState.offset.value / -swipeAreaHeight
        val motionProgress = max(min(swipeProgress, 1f), 0f)

        MotionLayout(
            motionScene = MotionScene(content = motionSceneContent),
            progress = motionProgress,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .background(color = colorScheme.primary)
                    .clickable {
                        scope.launch {
                            swipeableState.animateTo(1)
                        }
                    }
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        orientation = Orientation.Vertical
                    )
                    .layoutId("music_bottom_sheet")
            ) {
                Image(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
                Column {
                    Text(text = playerControllerUIState.currentSong.songName)
                    Text(text = playerControllerUIState.currentSong.author)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                }
            }

            PlayerScreen(
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            swipeableState.animateTo(1)
                        }
                    }
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        orientation = Orientation.Vertical,
                        enabled = playerControllerUIState.playerState != null,
                    )
                    .layoutId("music_player"),
                onEvent = onEvent,
                playerControllerUIState = playerControllerUIState
            )
        }
    }
}