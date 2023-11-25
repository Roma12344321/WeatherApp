package com.dev.weatherapp.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.weatherapp.R
import org.w3c.dom.Text

class DayViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val textViewDate = view.findViewById<TextView>(R.id.textViewDate)
    val textViewDegree = view.findViewById<TextView>(R.id.textViewDegree)
    val textViewCondition = view.findViewById<TextView>(R.id.textViewCondition)
    val imageViewCondition = view.findViewById<ImageView>(R.id.imageViewConditions)
}