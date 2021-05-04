package com.xiaoaxiao.graduatemanagementsystem.entity

import android.net.Uri
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.xiaoaxiao.graduatemanagementsystem.Utils.UriConverters

/**
 * 学生信息表
 */
@Entity
@TypeConverters(UriConverters::class)
data class Student(
    @PrimaryKey(autoGenerate = true)
    var studentId: Long = 0,
    val studentName: String,
    val sex: String,
    val studentNumber: String,
    val collegeId: Long,
    val professionId: Long,
    val provinceId: Long,
    var jobId: Long = 0,
    // 简历Name
    @Nullable
    var resumeUri: Uri? = null
)