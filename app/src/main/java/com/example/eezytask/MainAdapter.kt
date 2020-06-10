package com.example.eezytask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.calenderView.CalenderView
import com.example.eezytask.calenderView.CalenderViewAdapter
import com.example.eezytask.models.Data
import java.util.*
import kotlin.collections.ArrayList


class MainAdapter(val listner: CalenderViewAdapter.DateSelectedListner) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var list: ArrayList<Data> = ArrayList()
    fun setListData(lista: ArrayList<Data>)
    {
        list.clear()
        list.add(Data())
        list.addAll(lista)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                viewType,
                parent,
                false
            )
        return if (viewType==R.layout.calender_viewholder)
            CalenderVH(itemView)
        else
            MainHeaderVH(itemView)
    }

    override fun getItemCount(): Int {
        return  list.size;
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==0)
            R.layout.calender_viewholder
        else
            R.layout.header_view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        abstract fun bindData(data: Data)
    }
    inner class CalenderVH(itemView: View) : ViewHolder(itemView), CalenderViewAdapter.DateSelectedListner
    {
        val calenderView =itemView.findViewById<CalenderView>(R.id.calenderView)
        override fun bindData(data: Data) {
            calenderView.setDateSelectedListner(this)
        }

        override fun dateSelected(date: Date) {
            listner.dateSelected(date)
        }
    }


}