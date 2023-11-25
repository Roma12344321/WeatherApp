package com.dev.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dev.weatherapp.R
import com.dev.weatherapp.domain.Day

class DayListAdapter : ListAdapter<Day, DayViewHolder>(DayItemDiffCallBack()) {

    var onItemClickListener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DayViewHolder, position: Int) {
        val day: Day = getItem(position)
        viewHolder.textViewDate.text = day.data
        viewHolder.textViewDegree.text = day.averageTemperature.toString()
        Glide.with(viewHolder.itemView)
            .load("https:" + day.iconOfCondition)
            .into(viewHolder.imageViewCondition)
        viewHolder.textViewCondition.text = day.textOfCondition
        viewHolder.view.setOnClickListener {
            onItemClickListener?.onItemClick(day)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(day: Day)
    }
}