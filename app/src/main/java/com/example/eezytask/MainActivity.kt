package com.example.eezytask

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eezytask.models.Data
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val list = ArrayList<Data>()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        prepareData()
        initRecycler()
    }

    private fun prepareData() {
        list.add(Data("Morning","20 C",R.drawable.noon,false))
        list.add(Data("Morning","20 C",R.drawable.noon,false))
        list.add(Data("Noon","30 C",R.drawable.ic_rain_grey,false))
        list.add(Data("AfterNoon","26 C",R.drawable.noon,false))
        list.add(Data("Evening","26 C",R.drawable.ic_rain_grey,false))
        list.add(Data("Night","16 C",R.drawable.ic_rain_grey,false))
    }

    private fun initRecycler() {
        adapter = MainAdapter(list)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter= adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

}
