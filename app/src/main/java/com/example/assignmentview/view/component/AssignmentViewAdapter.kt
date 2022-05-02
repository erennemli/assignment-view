package com.example.assignmentview.view.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentview.R
import com.example.assignmentview.databinding.ItemAssignmentViewBinding
import com.example.assignmentview.view.uimodel.ImageUiModel

class AssignmentViewAdapter : RecyclerView.Adapter<AssignmentViewAdapter.AssignmentViewHolder>() {

    var items = mutableListOf<ImageUiModel>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemAssignmentViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_assignment_view, parent, false)
        return AssignmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.binding.image = items[position]
    }

    override fun getItemCount(): Int = items.size

    inner class AssignmentViewHolder(val binding: ItemAssignmentViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}