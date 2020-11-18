package com.ros.letscookapp.Details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.R
import kotlinx.android.synthetic.main.item_measure.view.*

class MeasureAdapter(var measure: List<MeasureModel>) : RecyclerView.Adapter<MeasureAdapter.HolderMeasure>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureAdapter.HolderMeasure {
        return HolderMeasure(LayoutInflater.from(parent.context).inflate(R.layout.item_measure, parent, false))

    }

    override fun getItemCount(): Int = measure.size

    override fun onBindViewHolder(holder: MeasureAdapter.HolderMeasure, position: Int) {
        val measure = measure[position]
        holder.view.tv_measure.text = measure.measure
    }

    class HolderMeasure(val view: View) : RecyclerView.ViewHolder(view)

}