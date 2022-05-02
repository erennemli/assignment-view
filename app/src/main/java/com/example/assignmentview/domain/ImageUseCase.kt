package com.example.assignmentview.domain

import com.example.assignmentview.data.repository.ImageRepository
import com.example.assignmentview.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.ResponseBody
import javax.inject.Inject

class ImageUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val imagesRepository: ImageRepository
) : UseCase<ResponseBody, ImageUseCase.Params>(dispatcher) {

    override suspend fun buildUseCase(params: Params): ResponseBody =
        imagesRepository.getImagesInBitmap(imageType = params.imageType)

    data class Params(val imageType: String?)
}