package com.example.openingtask.utility


sealed class ApiResponce<T>(val data : T ?= null, val message : String?= null) {
    class Loading<T> : ApiResponce<T>()
    class Success<T>(data: T?= null) : ApiResponce<T>(data = data)
    class Error<T>(message: String?) : ApiResponce<T>(message = message)
}