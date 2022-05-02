package com.example.assignmentview.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.assignmentview.R
import com.example.assignmentview.databinding.ComponentAssignmentViewBinding
import com.example.assignmentview.view.uimodel.ImageUiModel

class AssignmentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.assignmentView
) : FrameLayout(context, attrs, defStyleAttr) {

    var onBitmapSelectionClicked: (() -> Unit)? = null
    var onUrlSelectionClicked: (() -> Unit)? = null

    private val binder: ComponentAssignmentViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.component_assignment_view,
        this,
        true
    )

    private val itemAdapter by lazy { AssignmentViewAdapter() }

    init {
        binder.textViewBitmapSelection.setOnClickListener {
            onBitmapSelectionClicked?.invoke()
        }

        binder.textViewUrlSelection.setOnClickListener {
            onUrlSelectionClicked?.invoke()
        }
    }

    fun setup(items: List<ImageUiModel>) {
        binder.recyclerView.adapter = itemAdapter
        itemAdapter.items = items.toMutableList()
    }
}