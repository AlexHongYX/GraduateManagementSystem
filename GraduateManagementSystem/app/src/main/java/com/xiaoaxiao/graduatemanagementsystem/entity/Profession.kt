package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 专业表
 */
@Entity
data class Profession (
    @PrimaryKey(autoGenerate = true)
    var professionId: Long = 0,
    val professionName: String,
    val collegeId: Long
)