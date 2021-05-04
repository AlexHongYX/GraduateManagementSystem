package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.StudentOfferedJob

@Dao
interface StudentOfferedJobDao {

    @Query("select * from StudentOfferedJob where studentId = :studentId AND jobId = :jobId")
    fun findStudentOfferedJobByStudentIdAndJobId(studentId: Long,jobId: Long): List<StudentOfferedJob>

    @Insert
    fun insertStudentOfferedJob(studentOfferedJob: StudentOfferedJob)

    @Query("select * from StudentOfferedJob")
    fun allStudentOfferedJobs(): List<StudentOfferedJob>
}