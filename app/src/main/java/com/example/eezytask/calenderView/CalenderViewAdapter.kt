package com.example.eezytask.calenderView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eezytask.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalenderViewAdapter(
    val dates: ArrayList<Date>,
    val listner: DateSelectedListner
) : RecyclerView.Adapter<CalenderViewAdapter.ViewHolder>() {
    public var selected : Int = 0
    val dateFormatter =  SimpleDateFormat("dd", Locale.ENGLISH);
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
        for(i in startPos until startPos+7)
            datesInRange.add(dates[i])
        return datesInRange
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rg = itemView.findViewById<RadioGroup>(R.id.radioGroup)

        fun bind(datesForPosition: List<Date>) {
            if (selected!=0) {

                itemView.findViewById<RadioButton>(selected).performClick()
                setSelectedData(datesForPosition)
            }
            else setTodaysDate(datesForPosition)
            
            rg.setOnCheckedChangeListener { group, checkedId ->
                selected=checkedId
               setSelectedData(datesForPosition)
            }

            itemView.findViewById<RadioButton>(R.id.mon).text = dateFormatter.format(datesForPosition[0])
            itemView.findViewById<RadioButton>(R.id.tues).text = dateFormatter.format(datesForPosition[1])
            itemView.findViewById<RadioButton>(R.id.wed).text = dateFormatter.format(datesForPosition[2])
            itemView.findViewById<RadioButton>(R.id.thurs).text = dateFormatter.format(datesForPosition[3])
            itemView.findViewById<RadioButton>(R.id.fri).text = dateFormatter.format(datesForPosition[4])
            itemView.findViewById<RadioButton>(R.id.sat).text = dateFormatter.format(datesForPosition[5])
            itemView.findViewById<RadioButton>(R.id.sun).text = dateFormatter.format(datesForPosition[6])

        }

        private fun setTodaysDate(datesForPosition: List<Date>) {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val today = Date()
            val todayWithZeroTime: Date = formatter.parse(formatter.format(today))
            datesForPosition.forEachIndexed { index, date ->
                if (todayWithZeroTime.time==date.time)
                {
                    selected =getIdByPos(index)
                    itemView.findViewById<RadioButton>(selected).performClick()
                    return@forEachIndexed
                }
            }

        }

        private fun getIdByPos(i: Int): Int {
            return when (i) {
                0->R.id.mon
                1->R.id.tues
                2->R.id.wed
                3->   R.id.thurs
                4->   R.id.fri
                5-> R.id.sat
                6-> R.id.sun
                else -> R.id.mon
            }
        }

        fun setSelectedData(datesForPosition: List<Date>){
            when (selected) {
                R.id.mon -> listner.dateSelected(datesForPosition[0])
                R.id.tues -> listner.dateSelected(datesForPosition[1])
                R.id.wed -> listner.dateSelected(datesForPosition[2])
                R.id.thurs -> listner.dateSelected(datesForPosition[3])
                R.id.fri -> listner.dateSelected(datesForPosition[4])
                R.id.sat -> listner.dateSelected(datesForPosition[5])
                R.id.sun -> listner.dateSelected(datesForPosition[6])
            }
        }
    }

    interface DateSelectedListner{
        fun dateSelected(date:Date)
    }
}