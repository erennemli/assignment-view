package com.example.assignmentview.util.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory

private const val OFFSET = 0

fun decodeByteArray(byteArray: ByteArray): Bitmap? =
    BitmapFactory.decodeByteArray(byteArray, OFFSET, byteArray.size)