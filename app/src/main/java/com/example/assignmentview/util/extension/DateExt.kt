package com.example.assignmentview.util.extension

import java.util.Date
import java.util.Calendar

private const val ZERO_LONG = 0L

fun fetchCurrentTime(): Date = Calendar.getInstance().time

fun calculateTimeDiffInBetween(
    firstDate: Date,
    secondDate: Date?
): Long = firstDate.time - (secondDate?.time ?: ZERO_LONG)