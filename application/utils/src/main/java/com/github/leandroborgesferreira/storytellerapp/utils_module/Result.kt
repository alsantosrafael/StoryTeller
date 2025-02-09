package com.github.leandroborgesferreira.storytellerapp.utils_module

sealed class ResultData<T> {

    class Idle<T>: ResultData<T>()
    class Loading<T> : ResultData<T>()
    class Complete<T>(val data: T): ResultData<T>()
    class Error<T>(val exception: Exception) : ResultData<T>()
}

fun <T, R> ResultData<T>.map(fn: (T) -> R): ResultData<R> =
    when (this) {
        is ResultData.Complete -> ResultData.Complete(fn(this.data))
        is ResultData.Error -> ResultData.Error(this.exception)
        is ResultData.Idle -> ResultData.Idle()
        is ResultData.Loading -> ResultData.Loading()
    }
