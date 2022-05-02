package com.example.assignmentview.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {

    @GET("image/{image_type}")
    suspend fun getImagesInBitmap(
        @Path("image_type") imageType: String?
    ): ResponseBody
}