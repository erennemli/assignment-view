package com.example.assignmentview.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.assignmentview.R
import com.example.assignmentview.databinding.ComponentAssignmentViewBinding
import com.example.assignmentview.view.uimodel.ImageUiModel

class AssignmentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.assignmentView
) : FrameLayout(context, attrs, defStyleAttr), AdapterView.OnItemSelectedListener {

    var onUrlSelectionClicked: (() -> Unit)? = null
    var onSpinnerSelected: ((imageType: String) -> Unit)? = null

    private val binder: ComponentAssignmentViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.component_assignment_view,
        this,
        true
    )

    private val itemAdapter by lazy { AssignmentViewAdapter() }

    init {
        initializeSpinner()

        binder.textViewUrlSelection.setOnClickListener {
            onUrlSelectionClicked?.invoke()
        }
    }

    fun setup(items: List<ImageUiModel>) {
        binder.recyclerView.adapter = itemAdapter
        itemAdapter.items = items.toMutableList()
    }

    private fun initializeSpinner() {
        ArrayAdapter.createFromResource(
            context,
            R.array.imageTypes,
            R.layout.item_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binder.spinnerImageType.adapter = adapter
        }
        binder.spinnerImageType.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val imageType = when (position) {
            SpinnerItem.JPEG.ordinal -> context.getString(R.string.jpeg)
            SpinnerItem.PNG.ordinal -> context.getString(R.string.png)
            SpinnerItem.SVG.ordinal -> context.getString(R.string.svg)
            SpinnerItem.WEBP.ordinal -> context.getString(R.string.webp)
            else -> context.getString(R.string.jpeg)
        }
        onSpinnerSelected?.invoke(imageType)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // no-op
    }

    enum class SpinnerItem {
        JPEG,
        PNG,
        SVG,
        WEBP
    }
}