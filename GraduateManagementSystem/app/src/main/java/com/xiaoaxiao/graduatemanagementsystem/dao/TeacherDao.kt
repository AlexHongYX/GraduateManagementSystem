package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.Teacher

@Dao
interface TeacherDao {

    @Insert
    fun insertTeacher(teacher: Teacher)

    @Query("select * from Teacher")
    fun allTeachers(): List<Teacher>

    @Query("select * from Teacher where teacherNumber = :teacherNumber")
    fun findTeacherByTeacherNumber(teacherNumber: String): List<Teacher>
}