package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.StudentFavoriteJob

@Dao
interface StudentFavoriteJobDao {

    @Insert
    fun insertStudentFavoriteJob(studentFavoriteJob: StudentFavoriteJob): Long

    @Query("select * from StudentFavoriteJob")
    fun allStudentFavoriteJob(): List<StudentFavoriteJob>

    @Query("select * from StudentFavoriteJob where studentId = :studentId")
    fun findFavoriteJobsByStudentId(studentId: Long): List<StudentFavoriteJob>

    @Query("delete from StudentFavoriteJob where studentId = :studentId and jobId = :jobId")
    fun deleteJob(studentId: Long, jobId: Long)

}