package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 学生收藏工作关联表
 */
@Entity
data class StudentFavoriteJob (
    @PrimaryKey(autoGenerate = true)
    var studentFavoriteJobId: Long = 0,
    val jobId: Long,
    val studentId: Long
)