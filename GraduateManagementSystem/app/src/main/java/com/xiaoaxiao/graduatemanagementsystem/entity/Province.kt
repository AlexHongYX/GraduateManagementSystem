package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Province (
    @PrimaryKey(autoGenerate = true)
    var provinceId: Long = 0,
    val provinceName: String
)