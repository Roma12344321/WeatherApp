package com.dev.weatherapp.presentation.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.weatherapp.R

class HourViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val textViewHourDate = view.findViewById<TextView>(R.id.textViewHourDate)
    val textViewHourDegree = view.findViewById<TextView>(R.id.textViewHourDegree)
    val textViewHourCondition = view.findViewById<TextView>(R.id.textViewHourCondition)
    val imageViewHourCondition = view.findViewById<ImageView>(R.id.imageViewHourConditions)
}