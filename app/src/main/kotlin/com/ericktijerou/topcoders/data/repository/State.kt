package com.ericktijerou.topcoders.data.repository

sealed class State<T> {
    class Success<T>(val data: T) : State<T>()
    class Failed<T>(val message: String) : State<T>()
}
