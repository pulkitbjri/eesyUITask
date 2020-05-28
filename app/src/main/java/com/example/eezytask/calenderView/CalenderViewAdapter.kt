package com.example.eezytask.calenderView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.R


class CalenderViewAdapter : RecyclerView.Adapter<CalenderViewAdapter.ViewHolder>() {

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

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}