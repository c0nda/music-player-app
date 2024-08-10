package com.listener.musicplayerapp.domain.model


data class Song(
    val id: Int,
    val songName: String,
    val author: String,
    val duration: Long
)