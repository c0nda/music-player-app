package com.listener.musicplayerapp.domain.model

import android.net.Uri


data class Song(
    val id: String,
    val songName: String,
    val author: String,
    val uri: Uri
)