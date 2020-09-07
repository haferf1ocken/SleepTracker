package com.example.android.trackmysleepquality.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.utils.convertDurationToFormatted
import com.example.android.trackmysleepquality.utils.convertNumericQualityToString
import kotlinx.android.synthetic.main.list_item_sleep_night.view.*


class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.SleepNightViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepNightViewHolder {
        return SleepNightViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SleepNightViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class SleepNightViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sleepQuality: TextView = itemView.sleep_length
        private val quality: TextView = itemView.quality_string
        private val qualityImage: ImageView = itemView.quality_image

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepQuality.text =
                    convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
            fun from(parent: ViewGroup): SleepNightViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                return SleepNightViewHolder(view)
            }
        }
    }
}