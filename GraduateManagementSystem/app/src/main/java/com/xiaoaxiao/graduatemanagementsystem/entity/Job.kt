package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 工作信息表
 */
@Entity
data class Job (
    @PrimaryKey(autoGenerate = true)
    var jobId: Long = 0,
    val jobName: String,
    // 岗位性质：运营、产品、技术...
    val jobNature: String,
    val company: String,
    val salary: String,
    val provinceId: Long,
    val jobIntroduction: String
): Serializable