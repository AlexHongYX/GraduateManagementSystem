package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.Job
import com.xiaoaxiao.graduatemanagementsystem.entity.Student

@Dao
interface JobDao {

    @Insert
    fun insertJob(job: Job): Long

    @Query("select * from Job")
    fun allJobs(): List<Job>

    @Query("select * from Job where jobName = :jobName")
    fun findJobByName(jobName: String): Array<Job>

    @Query("select * from Job where jobId = :jobId")
    fun findJobById(jobId: Long): List<Job>
}