package com.listener.musicplayerapp.domain.repository

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    fun getAllSongs(): Flow<Result<List<Song>>>

}