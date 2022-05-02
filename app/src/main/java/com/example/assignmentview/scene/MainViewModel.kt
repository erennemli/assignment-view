package com.example.assignmentview.scene

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmentview.base.BaseViewModel
import com.example.assignmentview.domain.ImageUseCase
import com.example.assignmentview.util.extension.calculateTimeDiffInBetween
import com.example.assignmentview.util.extension.decodeByteArray
import com.example.assignmentview.util.extension.executeAsyncTask
import com.example.assignmentview.util.extension.fetchCurrentTime
import com.example.assignmentview.util.general.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    private val dispatcher: CoroutineDispatcher,
    application: Application
) : BaseViewModel(application) {

    val bitMap: MutableLiveData<Bitmap> = MutableLiveData()

    private var timeBeforeExecute: Date? = null

    fun fetchImagesInBitmap() = viewModelScope.launch {
        imageUseCase.run(UseCase.None).either(::handleFailure, ::handleImagesInBitmap)
    }

    private fun handleImagesInBitmap(image: ResponseBody) {
        viewModelScope.executeAsyncTask(
            dispatcher = dispatcher,
            doInBackground = {
                timeBeforeExecute = fetchCurrentTime()
                Log.d("Do in background:", "at $timeBeforeExecute")
                bitMap.postValue(decodeByteArray(image.bytes()))
            },

            onPostExecute = {
                val timeAfterExecute = fetchCurrentTime()
                val diff: Long = calculateTimeDiffInBetween(timeAfterExecute, timeBeforeExecute)
                Log.d("On post execute:", "at $timeAfterExecute")
                Log.d("Difference:", "$diff")
            }
        )
    }
}