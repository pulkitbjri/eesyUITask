package com.example.eezytask.models.typeAdapter

import com.example.eezytask.models.Plans
import com.example.eezytask.models.plan.Comments
import com.example.eezytask.models.plan.Header
import com.example.eezytask.models.plan.Restro
import com.google.gson.*
import java.lang.reflect.Type

internal class PlanModelDeserializer : JsonDeserializer<Plans?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Plans? {
        val jsonObject = json.asJsonObject
        val jsonType = jsonObject["type"]
        val type = jsonType.asString
        var typeModel: Plans? = null
        when (type) {
            "header" -> {
                typeModel = Gson().fromJson(json,Header::class.java)
            }
            "restro" -> {
                typeModel =Gson().fromJson(json,Restro::class.java)
            }
            "comments" -> {
                typeModel = Gson().fromJson(json,Comments::class.java)
            }
        }
        return typeModel
    }
}