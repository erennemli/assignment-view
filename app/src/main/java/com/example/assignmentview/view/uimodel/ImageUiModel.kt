package com.example.assignmentview.view.uimodel

import android.graphics.Bitmap
import java.util.Date

data class ImageUiModel(
    val bitmap: Bitmap? = null,
    val url: String? = null,
    val timeBeforeExecute: Date?
)