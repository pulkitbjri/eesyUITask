package com.example.eezytask

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eezytask.calenderView.CalenderViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() ,CalenderViewAdapter.DateSelectedListner{
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViewModel()

        observeData()
        initRecycler()

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val today = Date()
        val todayWithZeroTime: Date = formatter.parse(formatter.format(today))


        getDatesBetweenByWeek(todayWithZeroTime)
    }

    private fun initViewModel() {
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private fun observeData() {
        viewModel.liveData
            .debounce(500)
            .observe(this, Observer {
            recycler.post(Runnable { adapter.setListData( it.data!!) })

        })
    }

    private fun initRecycler() {
        adapter = MainAdapter(this)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter= adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }
    fun getDatesBetweenByWeek(
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
        viewModel.setRandomSampleData()
    }
    fun <T> LiveData<T>.debounce(duration: Long = 1000L) = MediatorLiveData<T>().also { mld ->
        val source = this
        val handler = Handler(Looper.getMainLooper())

        val runnable = Runnable {
            mld.value = source.value
        }

        mld.addSource(source) {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, duration)
        }
    }
}
