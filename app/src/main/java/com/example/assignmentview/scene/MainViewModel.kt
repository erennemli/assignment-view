package com.example.assignmentview.scene

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmentview.base.BaseViewModel
import com.example.assignmentview.domain.ImageUseCase
import com.example.assignmentview.util.extension.decodeByteArray
import com.example.assignmentview.util.extension.fetchCurrentTime
import com.example.assignmentview.util.general.UseCase
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

    fun fetchImagesInBitmap() = viewModelScope.launch {
        imageUseCase.run(UseCase.None).either(::handleFailure, ::handleImagesInBitmap)
    }

    private fun handleImagesInBitmap(image: ResponseBody) {
        val uiModel = ImageUiModel(
            bitmap = decodeByteArray(image.bytes()),
            timeBeforeExecute = fetchCurrentTime()
        )
        imageList.postValue(listOf(uiModel))
    }
}