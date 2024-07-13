package com.listener.musicplayerapp.utils

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Throwable?) : Result<T>()
    class Loading<T> : Result<T>()
}