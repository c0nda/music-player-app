package com.listener.musicplayerapp.data.local.device

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.listener.musicplayerapp.data.local.LocalDataSource
import com.listener.musicplayerapp.domain.model.Song
import com.listener.musicplayerapp.utils.Result
import com.listener.musicplayerapp.utils.ResultUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeviceStorage @Inject constructor(private val context: Context) : LocalDataSource {

    override fun getAllSongs(): Flow<Result<List<Song>>> = ResultUtils.requestFlow {
        val songs = mutableListOf<Song>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.AUTHOR,
            MediaStore.Audio.Media.DURATION
        )

        val selection: String? = null
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

        getSongsFromExternalStorage(songs, projection, selection, sortOrder)
        getSongsFromInternalStorage(songs, projection, selection, sortOrder)

        songs
    }

    private suspend fun getSongsFromExternalStorage(
        songs: MutableList<Song>,
        projection: Array<String>,
        selection: String?,
        sortOrder: String?
    ) {
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        iterateAllSongs(cursor, songs, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
    }

    private suspend fun getSongsFromInternalStorage(
        songs: MutableList<Song>,
        projection: Array<String>,
        selection: String?,
        sortOrder: String?
    ) {
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        iterateAllSongs(cursor, songs, MediaStore.Audio.Media.INTERNAL_CONTENT_URI)
    }

    private suspend fun iterateAllSongs(
        cursor: Cursor?,
        songs: MutableList<Song>,
        contentType: Uri
    ) {
        withContext(Dispatchers.IO) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))

                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))

                    val author =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.AUTHOR))

                    val uri =
                        ContentUris.withAppendedId(contentType, id.toLong())

                    if (title.endsWith(".mp3")) {
                        songs.add(
                            Song(
                                id = id,
                                songName = title,
                                author = author ?: "Unknown author",
                                uri = uri
                            )
                        )
                    }
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
    }
}