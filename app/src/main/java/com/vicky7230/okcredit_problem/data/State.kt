package com.vicky7230.okcredit_problem.data


sealed class State<out T : Any> {
    data class Success<out T : Any>(val data: T) : State<T>()
    data class Error(val exception: Exception) : State<Nothing>()
    object Loading : State<Nothing>()
}