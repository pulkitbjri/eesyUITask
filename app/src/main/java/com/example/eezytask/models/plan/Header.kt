package com.example.eezytask.models.plan

import com.example.eezytask.models.Plans

open class Header(

): Plans(){
    var hostImageUrl: String?=null
    var imageUrlList: List<String>?=null
    var text: String?=null
}