package com.example.eezytask.calenderView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.R
import java.util.*
import kotlin.collections.ArrayList


class CalenderViewAdapter(val dates: ArrayList<Date>) : RecyclerView.Adapter<CalenderViewAdapter.ViewHolder>() {
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
        return  dates.size/7;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getDatesForPosition(position))
    }

    private fun getDatesForPosition(position: Int):List<Date> {
        val datesInRange: ArrayList<Date> = ArrayList()
        val startPos= (position)*7
        for(i in startPos until position+7)
            datesInRange.add(dates[i])
        return datesInRange
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rg = itemView.findViewById<RadioGroup>(R.id.radioGroup)

        fun bind(datesForPosition: List<Date>) {
            itemView.findViewById<RadioButton>(selected).performClick()
            rg.setOnCheckedChangeListener { group, checkedId ->
                selected=checkedId
            }
        }

    }
}