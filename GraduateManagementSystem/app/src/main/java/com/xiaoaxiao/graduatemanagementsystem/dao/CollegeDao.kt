package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.College

@Dao
interface CollegeDao {

    @Insert
    fun insertCollege(college: College)

    @Query("select * from College")
    fun allColleges(): List<College>

    @Query("select * from College where collegeName = :collegeName")
    fun findCollegeByName(collegeName: String): Array<College>

    @Query("select * from College where collegeId = :collegeId")
    fun findCollegeById(collegeId: Long): Array<College>
}