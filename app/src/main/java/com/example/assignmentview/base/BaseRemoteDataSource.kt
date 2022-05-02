package com.example.assignmentview.base

import com.example.assignmentview.util.general.Failure

open class BaseRemoteDataSource {

    suspend fun <O> invoke(serviceFunction: suspend () -> O): O {
        return try {
            serviceFunction()
        } catch (exception: Exception) {
            throw asFailure(exception)
        }
    }

    private fun asFailure(exception: Exception): Failure {
        return if (exception is Failure) {
            exception
        } else Failure.UnknownError(exception)
    }
}