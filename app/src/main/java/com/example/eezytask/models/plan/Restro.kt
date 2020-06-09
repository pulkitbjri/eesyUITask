package com.example.eezytask.models.plan

import com.example.eezytask.models.Plans

open class Restro : Plans(){
    var address: String?=null
    var name: String?=null
    var foodType: String?=null
    var imageUrl: String?=null
    var inAccepted: Boolean?=null
    var time: String?=null
    var timeToNext: String?=null
}