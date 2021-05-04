package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 学生已签约公司，用于已签约学生信息展示(Tab2)
 */
@Entity
data class StudentOfferedJob (
    @PrimaryKey(autoGenerate = true)
    var studentFavoriteJobId: Long = 0,
    val jobId: Long,
    val studentId: Long
)