package com.example.eezytask.calenderView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.eezytask.R
import com.example.eezytask.helpers.SnapHelperOneByOne
import kotlinx.android.synthetic.main.calender_view.view.*


class CalenderView : FrameLayout {
    private lateinit var itemView: View

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
        setRecycler()
        }

    var direction : Int = 0 //0 left ,1 right
    private fun setRecycler() {
        recyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CalenderViewAdapter()
        val snapHelper: SnapHelper = SnapHelperOneByOne()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE){
                    val pos= ((recyclerView.layoutManager) as LinearLayoutManager).findFirstVisibleItemPosition()
                    recyclerView.adapter?.notifyItemChanged(pos)
                }

            }
        })
    }

}