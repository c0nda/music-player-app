package com.listener.musicplayerapp.domain.model


data class Song(
    val uri: String,
    val songName: String,
    val author: String,
    val duration: Int,
    val image: String
)