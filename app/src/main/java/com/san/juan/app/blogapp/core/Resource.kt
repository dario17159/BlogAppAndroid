package com.san.juan.app.blogapp.core

import java.lang.Exception

/**
 * @author Dario Carrizo on 8/2/2021
 **/
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
}
