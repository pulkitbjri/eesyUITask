package com.example.eezytask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eezytask.models.DataList
import com.example.eezytask.models.Plans
import com.example.eezytask.models.typeAdapter.PlanModelDeserializer
import com.google.gson.GsonBuilder
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainViewModel(application: Application) : AndroidViewModel(application) {
    var liveData = MutableLiveData<DataList>()
    var sampleList = ArrayList<DataList>()
    init {
        prepareData()
    }
    private fun setRandomSampleData() {
        liveData.value=getRandomElement(sampleList)
//        liveData.value=sampleList[0]
    }

    private fun prepareData() {

        getSampleData("sample1.json")
        getSampleData("sample2.json")
        getSampleData("sample3.json")
        getSampleData("sample4.json")
        setRandomSampleData()
    }
    private fun getSampleData(file:String){
        val string= loadJSONFromAsset(file)
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Plans::class.java, PlanModelDeserializer())
        val gson = gsonBuilder.create()

        val data = gson.fromJson(string, DataList::class.java)
        sampleList.add(data)
    }
    private fun loadJSONFromAsset(filename : String): String? {
        val jsonString: String
        try {
            jsonString = getApplication<Application>().assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
    private fun getRandomElement(list: ArrayList<DataList>): DataList? {
        val rand = Random()
        return list[rand.nextInt(list.size)]
    }
}