package com.listener.musicplayerapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

object ResultUtils {

    fun <T> requestFlow(resultFunc: suspend () -> T): Flow<Result<T>> {
        return flow<Result<T>> {
            emit(Result.Success(resultFunc()))
        }.onStart {
            emit(Result.Loading())
        }.catch { error ->
            emit(Result.Error(error))
        }
    }
}