package com.example.eezytask.calenderView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.R


class CalenderViewAdapter : RecyclerView.Adapter<CalenderViewAdapter.ViewHolder>() {
    public var selected : Int = R.id.mon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.calender_adapter,
                parent,
                false
            )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  20;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rg = itemView.findViewById<RadioGroup>(R.id.radioGroup)

        fun bind() {
            itemView.findViewById<RadioButton>(selected).performClick()
            rg.setOnCheckedChangeListener { group, checkedId ->
                selected=checkedId
            }
        }

    }
}