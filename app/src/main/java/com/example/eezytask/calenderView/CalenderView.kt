package com.example.eezytask.calenderView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.eezytask.R


class CalenderView : FrameLayout {
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
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.calender_view, this, false)
        this.addView(itemView)
    }

}