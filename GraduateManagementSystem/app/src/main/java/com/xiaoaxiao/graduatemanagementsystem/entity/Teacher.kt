package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 老师信息总表
 */
@Entity
data class Teacher (
    @PrimaryKey(autoGenerate = true)
    var teacherId: Long = 0,
    val teacherName: String,
    val sex: String,
    val teacherNumber: String,
    val collegeId: Long,
    val professionId: Long
)