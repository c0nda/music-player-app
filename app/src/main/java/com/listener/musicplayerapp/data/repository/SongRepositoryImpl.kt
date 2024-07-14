package com.listener.musicplayerapp.data.repository

import com.listener.musicplayerapp.data.local.LocalDataSource
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.repository.SongRepository
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : SongRepository {

    override fun getAllSongs(): Flow<Result<List<Song>>> = flow {
        localDataSource.getAllSongs()
    }
}