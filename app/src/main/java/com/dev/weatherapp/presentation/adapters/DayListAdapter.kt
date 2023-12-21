package com.dev.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dev.weatherapp.R
import com.dev.weatherapp.domain.Day
import java.text.SimpleDateFormat
import java.util.Date


class DayListAdapter : ListAdapter<Day, DayViewHolder>(DayItemDiffCallBack()) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.day_item,
            parent,
            false
        )
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DayViewHolder, position: Int) {
        val day: Day = getItem(position)
        viewHolder.textViewDate.text = parseDate(day.data)
        viewHolder.textViewDegree.text = day.averageTemperature.toString()
        Glide.with(viewHolder.itemView)
            .load("https:" + day.iconOfCondition)
            .into(viewHolder.imageViewCondition)
        viewHolder.textViewCondition.text = day.textOfCondition
        viewHolder.view.setOnClickListener {
            onItemClickListener?.onItemClick(day)
        }
    }

    private fun parseDate(inputData: String?): String? {
        val outputPattern = "dd-MM-yyyy"
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat(outputPattern!!)
        val date: Date
        var outputDateStr: String? = null
        try {
            date = inputFormat.parse(inputData)
            outputDateStr = outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outputDateStr
    }

    interface OnItemClickListener {
        fun onItemClick(day: Day)
    }
}