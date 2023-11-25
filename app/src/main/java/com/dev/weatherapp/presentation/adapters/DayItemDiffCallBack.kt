package com.dev.weatherapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dev.weatherapp.domain.Day

class DayItemDiffCallBack : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.data == newItem.data
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}