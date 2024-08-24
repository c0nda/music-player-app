package com.listener.musicplayerapp.domain.model

import android.net.Uri


data class Song(
    val id: Int,
    val songName: String,
    val author: String,
    val duration: Long,
    val uri: Uri
)