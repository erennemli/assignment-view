package com.example.assignmentview.scene

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmentview.base.BaseViewModel
import com.example.assignmentview.domain.ImageUseCase
import com.example.assignmentview.util.extension.decodeByteArray
import com.example.assignmentview.util.extension.fetchCurrentTime
import com.example.assignmentview.util.listOfImageUrls
import com.example.assignmentview.view.uimodel.ImageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    application: Application
) : BaseViewModel(application) {

    val imageList: MutableLiveData<List<ImageUiModel>> = MutableLiveData()

    fun fetchImages(isBitmap: Boolean, imageType: String? = null) {
        if (isBitmap) {
            fetchImagesInBitmap(imageType)
        } else {
            fetchImagesInUrl()
        }
    }

    private fun fetchImagesInBitmap(imageType: String?) = viewModelScope.launch {
        imageUseCase.run(params = ImageUseCase.Params(imageType = imageType))
            .either(::handleFailure, ::handleImagesInBitmap)
    }

    private fun fetchImagesInUrl() = viewModelScope.launch {
        val listOfImageUrls = listOfImageUrls.map {
            ImageUiModel(
                url = it,
                timeBeforeExecute = fetchCurrentTime()
            )
        }
        imageList.postValue(listOfImageUrls)
    }

    private fun handleImagesInBitmap(image: ResponseBody) {
        val uiModel = ImageUiModel(
            bitmap = decodeByteArray(image.bytes()),
            timeBeforeExecute = fetchCurrentTime()
        )
        imageList.postValue(listOf(uiModel))
    }
}