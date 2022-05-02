package com.example.assignmentview.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ImageService {

    @GET(IMAGE_JPEG)
    suspend fun getImagesInJpeg(): ResponseBody

    companion object {
        private const val IMAGE_JPEG = "image/jpeg"
    }
}