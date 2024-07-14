package com.listener.musicplayerapp.data.local

import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllSongs(): Flow<Result<List<Song>>>

}