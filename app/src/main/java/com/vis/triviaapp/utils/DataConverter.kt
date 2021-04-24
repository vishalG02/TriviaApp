package com.vis.triviaapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vis.triviaapp.model.QuestionAndResponse
import java.io.Serializable
import java.lang.reflect.Type


class DataConverter  {

    @TypeConverter // note this annotation
    fun fromOptionValuesList(optionValues: List<QuestionAndResponse?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<QuestionAndResponse?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toOptionValuesList(optionValuesString: String?): List<QuestionAndResponse>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<QuestionAndResponse?>?>() {}.type
        return gson.fromJson<List<QuestionAndResponse>>(optionValuesString, type)
    }
}