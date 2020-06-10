package com.example.eezytask.calenderView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.eezytask.R
import com.example.eezytask.helpers.SnapHelperOneByOne
import kotlinx.android.synthetic.main.calender_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalenderView : FrameLayout, CalenderViewAdapter.DateSelectedListner {
    private var listner: CalenderViewAdapter.DateSelectedListner?=null
    private lateinit var itemView: View
    lateinit var dates :ArrayList<Date>

    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    {
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    {
        init(context)
    }
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)
    {
        init(context)
    }
    private fun init(context: Context?) {
        itemView = LayoutInflater.from(context).inflate(R.layout.calender_view, this, false)
        this.addView(itemView)
        initCalenderDates()
        setRecycler()
    }

    private fun initCalenderDates() {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val today = Date()
        val todayWithZeroTime: Date = formatter.parse(formatter.format(today))
        dates=getDatesBetween(todayWithZeroTime)

        dateSelected(todayWithZeroTime)
    }

    private fun setRecycler() {
        recyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CalenderViewAdapter(dates,this)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if ( newState == RecyclerView.SCROLL_STATE_IDLE){
                    val pos= ((recyclerView.layoutManager) as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    if (pos>=0)
                        recyclerView.adapter?.notifyItemChanged(pos)
                    Log.i("aaa", "onScrollStateChanged: "+pos)
                }

            }
        })
    }

    fun getDatesBetween(
        startDate: Date
    ): ArrayList<Date> {
        val weekDaysPassed= getWeekDaysPassed(startDate)
        val newStartDate = getNewStartDate(weekDaysPassed,startDate)
        val cal = Calendar.getInstance()
        cal.time=newStartDate
        cal.add(Calendar.DATE, 168)
        val endDate = cal.time

        val datesInRange: ArrayList<Date> = ArrayList()
        val calendar: Calendar = GregorianCalendar()
        calendar.time = newStartDate
        val endCalendar: Calendar = GregorianCalendar()
        endCalendar.time = endDate
        while (calendar.before(endCalendar)) {
            val result: Date = calendar.getTime()
            datesInRange.add(result)
            calendar.add(Calendar.DATE, 1)
        }
        return datesInRange
    }

    private fun getNewStartDate(weekDaysPassed: Int, startDate: Date): Date {
        val d = startDate
        val dateBefore =  Date(d.getTime() - weekDaysPassed * 24 * 3600 * 1000 )
        return dateBefore
    }

    private fun getWeekDaysPassed(startDate: Date): Int {
        val c = Calendar.getInstance()
        c.time = startDate
        c.set(Calendar.DAY_OF_WEEK,-3)
        val dayOfWeek = c[Calendar.DAY_OF_WEEK]
        if (dayOfWeek==1)
            return 6
        else
            return dayOfWeek-2
    }

    override fun dateSelected(date: Date) {
        val dateFormatter =  SimpleDateFormat("EE dd, MMMM YYYY", Locale.ENGLISH);
        selectedDate.text=dateFormatter.format(date)
        listner?.dateSelected(date)
    }

    fun setDateSelectedListner(listner:CalenderViewAdapter.DateSelectedListner) {
        this.listner=listner
    }

}