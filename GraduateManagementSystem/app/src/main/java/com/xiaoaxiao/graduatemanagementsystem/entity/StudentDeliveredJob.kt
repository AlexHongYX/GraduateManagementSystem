package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 学生已投递工作关联表
 */
@Entity
data class StudentDeliveredJob (
    @PrimaryKey(autoGenerate = true)
    var studentDeliveredJobId: Long = 0,
    val jobId: Long,
    val studentId: Long
)