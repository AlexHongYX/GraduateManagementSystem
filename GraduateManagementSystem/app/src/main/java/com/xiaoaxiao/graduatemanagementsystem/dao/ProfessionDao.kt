package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.Profession

@Dao
interface ProfessionDao {

    @Insert
    fun insertProfession(profession: Profession)

    @Query("select * from Profession")
    fun allProfessions(): List<Profession>

    @Query("select * from Profession where professionName = :professionName")
    fun findProfessionByName(professionName: String): Array<Profession>

    @Query("select * from Profession where professionId = :professionId")
    fun findProfessionById(professionId: Long): Array<Profession>
}