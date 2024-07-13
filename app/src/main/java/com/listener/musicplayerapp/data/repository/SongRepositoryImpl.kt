package com.listener.musicplayerapp.data.repository

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.domain.repository.SongRepository
import com.listener.musicplayerapp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(private val context: Context) : SongRepository {

    override fun getAllSongs(): Flow<Result<List<Song>>> = flow {

        val songs = mutableListOf<Song>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.AUTHOR,
            MediaStore.Audio.Media.DURATION
        )

        getSongsFromExternalStorage(songs, projection)
        getSongsFromInternalStorage(songs, projection)

        if (songs.isNotEmpty()) {
            emit(Result.Success(songs))
        }
    }

    private fun getSongsFromExternalStorage(songs: MutableList<Song>, projection: Array<String>) {
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        iterateAllSongs(cursor, songs)
    }

    private fun getSongsFromInternalStorage(songs: MutableList<Song>, projection: Array<String>) {
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        iterateAllSongs(cursor, songs)
    }

    private fun iterateAllSongs(cursor: Cursor?, songs: MutableList<Song>) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id =
                        cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    val duration = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                    val artist =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                    songs.add(
                        Song(
                            id = id,
                            songName = title,
                            author = artist,
                            duration = duration
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
    }
}