package com.example.assignmentview.scene

import android.os.Bundle
import com.example.assignmentview.R
import com.example.assignmentview.base.BaseActivity
import com.example.assignmentview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.lifecycleOwner = this
    }
}