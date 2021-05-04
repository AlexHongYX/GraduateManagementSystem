package com.xiaoaxiao.graduatemanagementsystem.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LongConverters {

    @TypeConverter
    fun stringToObject(value: String): MutableList<Long> {
        val listType = object : TypeToken<MutableList<Long>>() {

        }.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun objectToString(list: MutableList<Long>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}