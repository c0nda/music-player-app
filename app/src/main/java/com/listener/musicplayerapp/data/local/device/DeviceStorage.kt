package com.listener.musicplayerapp.data.local.device

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.listener.musicplayerapp.data.local.LocalDataSource
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.utils.Result
import com.listener.musicplayerapp.utils.ResultUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceStorage @Inject constructor(private val context: Context) : LocalDataSource {

    override fun getAllSongs(): Flow<Result<List<Song>>> {
        return ResultUtils.requestFlow {

            val songs = mutableListOf<Song>()

            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.AUTHOR,
                MediaStore.Audio.Media.DURATION
            )

            getSongsFromExternalStorage(songs, projection)
            getSongsFromInternalStorage(songs, projection)

            songs
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
                    val duration =
                        cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION).toLong()
                    val author =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.AUTHOR))
                    songs.add(
                        Song(
                            id = id,
                            songName = title,
                            author = author,
                            duration = duration
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
    }
}