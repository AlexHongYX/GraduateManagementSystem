package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.StudentDeliveredJob

@Dao
interface StudentDeliveredJobDao {

    @Insert
    fun insertStudentDeliveredJob(studentDeliveredJob: StudentDeliveredJob)

    @Query("select * from StudentDeliveredJob")
    fun allStudentDeliveredJobs(): List<StudentDeliveredJob>

    @Query("select * from StudentDeliveredJob where studentId = :studentId and jobId = :jobId")
    fun findStudentDeliveredJob(studentId: Long,jobId: Long): Array<StudentDeliveredJob>

    @Query("select * from StudentDeliveredJob where studentId = :studentId")
    fun findStudentDeliveredJobByStudentId(studentId: Long): Array<StudentDeliveredJob>

    @Query("delete from StudentDeliveredJob where studentId = :studentId and jobId = :jobId")
    fun deleteJob(studentId: Long,jobId: Long)
}