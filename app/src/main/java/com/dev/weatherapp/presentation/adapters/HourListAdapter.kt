package com.dev.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dev.weatherapp.R
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.Hour

class HourListAdapter : ListAdapter<Hour, HourViewHolder>(HourItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hour_item, parent, false)
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: HourViewHolder, position: Int) {
        val hour = getItem(position)
        viewHolder.textViewHourDate.text = hour.time
        viewHolder.textViewHourDegree.text = hour.temperature.toString()
        viewHolder.textViewHourCondition.text = hour.textOfHourConditions
        Glide.with(viewHolder.itemView)
            .load("https:" + hour.iconOfHourConditions)
            .into(viewHolder.imageViewHourCondition)
    }
}
