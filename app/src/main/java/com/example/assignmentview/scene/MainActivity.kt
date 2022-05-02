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

        initListeners()

        observeData()
    }

    private fun observeData() {
        viewModel.imageList.observe(
            this,
            {
                binder.assignmentView.setup(it)
            }
        )
    }

    private fun initListeners() {
        binder.assignmentView.onBitmapSelectionClicked = {
            viewModel.fetchImages(true)
        }

        binder.assignmentView.onUrlSelectionClicked = {
            viewModel.fetchImages(false)
        }
    }
}