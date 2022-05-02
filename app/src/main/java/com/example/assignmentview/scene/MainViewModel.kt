package com.example.assignmentview.scene

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmentview.base.BaseViewModel
import com.example.assignmentview.domain.ImageUseCase
import com.example.assignmentview.util.extension.decodeByteArray
import com.example.assignmentview.util.general.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    application: Application
) : BaseViewModel(application) {

    val bitMap: MutableLiveData<Bitmap> = MutableLiveData()

    fun fetchImagesInBitmap() = viewModelScope.launch {
        imageUseCase.run(UseCase.None).either(::handleFailure, ::handleImagesInBitmap)
    }

    private fun handleImagesInBitmap(image: ResponseBody) {
        bitMap.postValue(decodeByteArray(image.bytes()))
    }
}