package com.example.assignmentview.util.databinding

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.assignmentview.R
import com.example.assignmentview.util.extension.calculateTimeDiffInBetween
import com.example.assignmentview.util.extension.fetchCurrentTime
import com.example.assignmentview.util.general.Constants.CUSTOM_TARGET_SIZE
import com.example.assignmentview.util.general.Constants.IMAGE_SIZE
import com.example.assignmentview.view.uimodel.ImageUiModel
import java.util.Date

object ViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:imageFromBoth")
    fun loadImage(
        iv: ImageView,
        imageUiModel: ImageUiModel?
    ) {
        var timeBeforeExecute: Date? = null
        Glide
            .with(iv.context)
            .load(imageUiModel?.url ?: imageUiModel?.bitmap)
            .override(IMAGE_SIZE, IMAGE_SIZE)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable?>(CUSTOM_TARGET_SIZE, CUSTOM_TARGET_SIZE) {
                override fun onLoadStarted(placeholder: Drawable?) {
                    timeBeforeExecute = fetchCurrentTime()
                    Log.d(
                        iv.context.getString(R.string.print_post_execute),
                        iv.context.getString(R.string.print_at, timeBeforeExecute)
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // no-op
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    iv.setImageDrawable(resource)
                    val timeAfterExecute = fetchCurrentTime()
                    timeBeforeExecute?.let {
                        val diff: Long = calculateTimeDiffInBetween(
                            timeAfterExecute,
                            it
                        )
                        Log.d(
                            iv.context.getString(R.string.print_post_execute),
                            iv.context.getString(R.string.print_at, timeAfterExecute)
                        )
                        Log.d(
                            iv.context.getString(R.string.print_time_difference),
                            iv.context.getString(R.string.print_diff, diff.toString())
                        )
                    }
                }
            })
    }
}