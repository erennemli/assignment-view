package com.example.assignmentview.util.extension

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <R> CoroutineScope.executeAsyncTask(
    dispatcher: CoroutineDispatcher,
    onPreExecute: (() -> Unit)? = null,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit
) = launch {
    onPreExecute?.let {
        it()
    }
    val result =
        withContext(dispatcher) {
            doInBackground()
        }
    onPostExecute(result)
}