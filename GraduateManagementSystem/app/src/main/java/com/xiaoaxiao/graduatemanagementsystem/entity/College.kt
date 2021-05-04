package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 学院表
 */
@Entity
data class College (
    @PrimaryKey(autoGenerate = true)
    var collegeId: Long = 0,
    val collegeName: String
)