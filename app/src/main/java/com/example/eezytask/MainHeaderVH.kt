package com.example.eezytask

import android.graphics.Color
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.eezytask.models.Data
import com.example.eezytask.models.Plans
import com.example.eezytask.models.plan.Comments
import com.example.eezytask.models.plan.Header
import com.example.eezytask.models.plan.Restro
import com.example.eezytask.slotsCustomView.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class MainHeaderVH(itemView: View) : MainAdapter.ViewHolder(itemView)
{
    val card : CardView = itemView.findViewById(R.id.card)
    val name : TextView = itemView.findViewById(R.id.name)
    private val temp: TextView = itemView.findViewById(R.id.temp)
    val iv_temp : ImageView = itemView.findViewById(R.id.iv_temp)
    private val fab: FloatingActionButton = itemView.findViewById(R.id.floatingActionButton)
    val headerLay : ConstraintLayout = itemView.findViewById(R.id.innerConstraint)
    val bottomLay : ConstraintLayout = itemView.findViewById(R.id.expandableLay)
    val diff : Button = itemView.findViewById(R.id.diff)
    val rest1View : ConstraintLayout = itemView.findViewById(R.id.include)
    val rest2View : ConstraintLayout = itemView.findViewById(R.id.include3)


    private fun getWeatherDrawable(imageId: Int?): Int {
        if (imageId==1) return R.drawable.noon
        else return R.drawable.ic_rain_grey
    }

    override fun bindData(data: Data) {
        name.text = data.timeSlot
        temp.text = data.temp
        iv_temp.setImageDrawable(ContextCompat.getDrawable(itemView.context,getWeatherDrawable(data.image)))
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

        setCardsData(data.plan)
    }

    private fun setCardsData(plan: List<Plans>?) {
        var restroCount= 1
        plan?.forEach {
            if (it is Restro){
                setRestroData(it, if (restroCount==1)  rest1View else rest2View )
                if (restroCount==1){
                    itemView.findViewById<Button>(R.id.diff).text=it.timeToNext
                }
                restroCount++
            }
            if (it is Comments)
                setCommentsData(it)
            if (it is Header)
                setHeaderData(it)
        }
    }

    private fun setHeaderData(header: Header) {

        itemView.findViewById<TextView>(R.id.hostName).text=header.text
        Picasso.get().load(header.hostImageUrl)  .resize(50, 50)
            .into(itemView.findViewById<ImageView>(R.id.hostImage))
        Picasso.get().load(header.imageUrlList!![0])  .resize(50, 50)
            .into(itemView.findViewById<ImageView>(R.id.image1))
        Picasso.get().load(header.imageUrlList!![1])  .resize(50, 50)
            .into(itemView.findViewById<ImageView>(R.id.image2))
        Picasso.get().load(header.imageUrlList!![2])  .resize(50, 50)
            .into(itemView.findViewById<ImageView>(R.id.image3))

    }

    private fun setCommentsData(comments: Comments) {

        itemView.findViewById<TextView>(R.id.commentCount).text=("${comments.count} Comments")

    }

    private fun setRestroData(restro: Restro, view: View) {
        view.findViewById<TextView>(R.id.restroName).text=restro.name
        view.findViewById<TextView>(R.id.time).text=restro.time
        view.findViewById<TextView>(R.id.address).text=restro.address
        view.findViewById<TextView>(R.id.dish).text=restro.foodType
        Picasso.get().load(restro.imageUrl)  .resize(200, 140)
            .into(view.findViewById<ImageView>(R.id.imageVenue))
        val accept =view.findViewById<Button>(R.id.accept)
        val accepted =view.findViewById<Button>(R.id.accepted)
        val decline =view.findViewById<Button>(R.id.decline)
        if (restro.inAccepted!!){
            accepted.visibility= VISIBLE
            decline.visibility= GONE
            accept.visibility=GONE
        }
        else{
            accepted.visibility= GONE
            decline.visibility= VISIBLE
            accept.visibility= VISIBLE
        }
        accept.setOnClickListener {
            accepted.visibility= VISIBLE
            decline.visibility= GONE
            accept.visibility=GONE
            restro.inAccepted=true
        }
    }

    private fun shrink() {
        fab.visibility= View.VISIBLE
        val layoutParams = card.layoutParams as (ViewGroup.MarginLayoutParams)
        layoutParams.setMargins(
            Utils.dpToPx(10f,itemView.context), Utils.dpToPx(5f,itemView.context)
            , Utils.dpToPx(10f,itemView.context), Utils.dpToPx(5f,itemView.context))
        headerLay.setBackgroundColor(Color.WHITE)
        bottomLay.visibility= GONE
        card.cardElevation= Utils.dpToPx(2f,itemView.context).toFloat()
        name.setTextColor(ContextCompat.getColor(itemView.context,R.color.textColor1))
        temp.setTextColor(ContextCompat.getColor(itemView.context,R.color.textColor1))
        iv_temp.setColorFilter(
            ContextCompat.getColor(itemView.context, R.color.textColor1),
            android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    fun expand() {
        fab.visibility= GONE
        val layoutParams = card.layoutParams as (ViewGroup.MarginLayoutParams)
        layoutParams.setMargins(
            Utils.dpToPx(5f,itemView.context), Utils.dpToPx(5f,itemView.context)
            , Utils.dpToPx(5f,itemView.context), Utils.dpToPx(5f,itemView.context))
        headerLay.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorPrimary))
        bottomLay.visibility= View.VISIBLE
        card.cardElevation= Utils.dpToPx(0f,itemView.context).toFloat()
        name.setTextColor(Color.WHITE)
        temp.setTextColor(Color.WHITE)
        iv_temp.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);
    }
}