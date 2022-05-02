package com.example.assignmentview.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.assignmentview.util.general.Failure

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected open fun handleFailure(failure: Failure) {
        Log.d("Network request:", "FAILED!")
    }
}