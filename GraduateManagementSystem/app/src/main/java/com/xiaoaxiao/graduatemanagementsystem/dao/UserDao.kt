package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.*
import com.xiaoaxiao.graduatemanagementsystem.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun allUsers(): List<User>

    @Query("select * from User where userNumber = :userNumber")
    fun findUserFromUserNumber(userNumber: String): Array<User>

    @Query("select * from User where userNumber = :userNumber and password = :password")
    fun findUserFromUserNumberAndPassword(userNumber: String,password: String): Array<User>
}