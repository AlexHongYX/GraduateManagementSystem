package com.xiaoaxiao.graduatemanagementsystem.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户信息表
 */
@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0,
    val type: String,
    val userNumber: String,
    val password: String,
    // 头像路径
    var headPortraitPath: String? = null
)