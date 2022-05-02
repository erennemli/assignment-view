package com.example.assignmentview.data.repository

import com.example.assignmentview.data.datasource.ImageRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(
    private val imagesRemoteDataSource: ImageRemoteDataSource
) {

    suspend fun getImagesInBitmap() =
        imagesRemoteDataSource.getImagesInBitmap()
}