package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xiaoaxiao.graduatemanagementsystem.entity.Student

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student): Long

    @Query("select * from Student")
    fun allStudents(): List<Student>

    @Query("select * from Student where studentNumber = :studentNumber")
    fun findStudentByStudentNumber(studentNumber: String): Array<Student>

    @Update
    fun updateStudent(student: Student)

    @Query("select * from Student where studentId = :studentId")
    fun findStudentByStudentId(studentId: Long): List<Student>
}