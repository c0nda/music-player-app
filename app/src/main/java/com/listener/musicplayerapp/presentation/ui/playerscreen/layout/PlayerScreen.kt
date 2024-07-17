package com.listener.musicplayerapp.presentation.ui.playerscreen.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.listener.musicplayerapp.R

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier
) {
    var progress by rememberSaveable { mutableStateOf(0f) }
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            TopBar()
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (image, textTitle, textAuthor, slider, timeRow) = createRefs()
                Image(
                    modifier = Modifier
                        .constrainAs(image) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .size(280.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = MaterialTheme.colorScheme.primary),
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .constrainAs(textTitle) {
                            top.linkTo(image.bottom)
                            start.linkTo(image.start)
                        }
                        .padding(top = 10.dp),
                    text = "Song Name",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = Modifier
                        .constrainAs(textAuthor) {
                            start.linkTo(image.start)
                            top.linkTo(textTitle.bottom)
                        },
                    text = "Author",
                    style = MaterialTheme.typography.bodyMedium
                )
                Slider(
                    value = progress,
                    modifier = Modifier
                        .constrainAs(slider) {
                            top.linkTo(textAuthor.bottom)
                            start.linkTo(image.start)
                        }
                        .width(280.dp),
                    onValueChange = { progress = it }
                )
                Row(
                    modifier = Modifier
                        .constrainAs(timeRow) {
                            start.linkTo(image.start)
                            top.linkTo(slider.bottom)
                        }
                        .width(280.dp)
                ){
                    Text(text = "0.00")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "3.00")
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
                IconButton(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(R.drawable.shuffle_music),
                    contentDescription = null
                )
            }
        }
    }
}


@Preview(showBackground = true, heightDp = 640, widthDp = 360)
@Composable
private fun PlayerScreenPreview() {
    PlayerScreen()
}