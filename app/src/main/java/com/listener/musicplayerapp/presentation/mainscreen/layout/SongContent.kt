package com.listener.musicplayerapp.presentation.mainscreen.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.listener.musicplayerapp.domain.model.Song

@Composable
fun SongContent(
    song: Song,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            onClick = onClickCard
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = MaterialTheme.colorScheme.primary)
                        .size(50.dp),
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
                Column {
                    Text(
                        text = song.songName,
                        modifier = Modifier
                            .width(200.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = song.author)
                }
            }
        }
    }
}