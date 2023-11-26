package com.dev.weatherapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dev.weatherapp.domain.Hour

class HourItemDiffCallBack : DiffUtil.ItemCallback<Hour>() {
    override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem.textOfHourConditions == newItem.textOfHourConditions
    }

    override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }
}