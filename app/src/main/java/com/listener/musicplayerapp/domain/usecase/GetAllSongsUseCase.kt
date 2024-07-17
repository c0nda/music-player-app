package com.listener.musicplayerapp.domain.usecase

import com.listener.musicplayerapp.domain.repository.SongRepository
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(private val songRepository: SongRepository) {

    fun execute() = songRepository.getAllSongs()
}