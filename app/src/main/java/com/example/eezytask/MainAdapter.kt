package com.example.eezytask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.models.Data
import com.example.eezytask.models.Plans
import com.example.eezytask.models.plan.Comments
import com.example.eezytask.models.plan.Header
import com.example.eezytask.models.plan.Restro
import com.example.eezytask.slotsCustomView.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
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
    class CalenderVH(itemView: View) : ViewHolder(itemView)
    {
        override fun bindData(data: Data) {
        }
    }


}