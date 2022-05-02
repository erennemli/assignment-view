package com.example.assignmentview.util.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.IOException

sealed class Failure : IOException(), Parcelable {
    @Parcelize
    class UnknownError(val exception: Exception) : Failure()
}