package com.listener.musicplayerapp.domain.repository

import com.listener.musicplayerapp.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    fun getAllSongs(): Flow<List<Song>>

}