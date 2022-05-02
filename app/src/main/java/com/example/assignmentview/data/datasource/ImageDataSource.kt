package com.example.assignmentview.data.datasource

import com.example.assignmentview.base.BaseRemoteDataSource
import com.example.assignmentview.data.api.ImageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRemoteDataSource @Inject constructor(
    private val imagesService: ImageService
) : BaseRemoteDataSource() {

    suspend fun getImagesInJpeg() = invoke {
        imagesService.getImagesInJpeg()
    }
}