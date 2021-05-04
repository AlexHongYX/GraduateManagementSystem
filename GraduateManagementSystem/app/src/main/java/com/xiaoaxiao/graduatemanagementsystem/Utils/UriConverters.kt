package com.xiaoaxiao.graduatemanagementsystem.Utils

import android.net.Uri
import androidx.room.TypeConverter

class UriConverters {

    @TypeConverter
    fun stringToUri(value: String): Uri? {
        if (value.isEmpty()) {
            return null
        }
        return Uri.parse(value)
    }

    @TypeConverter
    fun uriToString(uri: Uri?): String {
        return uri?.toString() ?: ""
    }
}