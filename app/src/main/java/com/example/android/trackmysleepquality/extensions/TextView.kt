package com.example.android.trackmysleepquality.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.utils.convertDurationToFormatted
import com.example.android.trackmysleepquality.utils.convertNumericQualityToString

@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight?) {
    item?.let {
        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight?) {
    item?.let {
        text = convertNumericQualityToString(item.sleepQuality, context.resources)
    }
}