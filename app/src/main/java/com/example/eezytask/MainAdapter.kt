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
import com.example.eezytask.slotsCustomView.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainAdapter(val list: ArrayList<Data>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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

    class MainHeaderVH(itemView: View) : ViewHolder(itemView)
    {
        val card :CardView = itemView.findViewById(R.id.card)
        val name :TextView = itemView.findViewById(R.id.name)
        private val temp:TextView = itemView.findViewById(R.id.temp)
        val iv_temp :ImageView = itemView.findViewById(R.id.iv_temp)
        private val fab: FloatingActionButton = itemView.findViewById(R.id.floatingActionButton)
        val headerLay : ConstraintLayout = itemView.findViewById(R.id.innerConstraint)
        val bottomLay : ConstraintLayout = itemView.findViewById(R.id.expandableLay)



        override fun bindData(data: Data) {
            name.text = data.timeSlot
            temp.text = data.temp
            iv_temp.setImageDrawable(ContextCompat.getDrawable(itemView.context,data.image))
            fab.setOnClickListener {
                card.performClick()
            }

            card.setOnClickListener {
                if (!data.isExpanded) {
                    expand()
                    data.isExpanded=true
                }
                else {
                    shrink()
                    data.isExpanded=false
                }
            }
            if (data.isExpanded) expand()
            else shrink()

        }

        private fun shrink() {
            fab.visibility= VISIBLE
            val layoutParams = card.layoutParams as (ViewGroup.MarginLayoutParams)
            layoutParams.setMargins(Utils.dpToPx(10f,itemView.context), Utils.dpToPx(5f,itemView.context)
                , Utils.dpToPx(10f,itemView.context), Utils.dpToPx(5f,itemView.context))
            headerLay.setBackgroundColor(Color.WHITE)
            bottomLay.visibility= GONE
            card.cardElevation= Utils.dpToPx(2f,itemView.context).toFloat()
            name.setTextColor(ContextCompat.getColor(itemView.context,R.color.textColor1))
            temp.setTextColor(ContextCompat.getColor(itemView.context,R.color.textColor1))
            iv_temp.setColorFilter(ContextCompat.getColor(itemView.context, R.color.textColor1), android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        fun expand() {
            fab.visibility= GONE
            val layoutParams = card.layoutParams as (ViewGroup.MarginLayoutParams)
            layoutParams.setMargins(Utils.dpToPx(5f,itemView.context), Utils.dpToPx(5f,itemView.context)
                , Utils.dpToPx(5f,itemView.context), Utils.dpToPx(5f,itemView.context))
            headerLay.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorPrimary))
            bottomLay.visibility= VISIBLE
            card.cardElevation= Utils.dpToPx(0f,itemView.context).toFloat()
            name.setTextColor(Color.WHITE)
            temp.setTextColor(Color.WHITE)
            iv_temp.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);
        }


    }
}