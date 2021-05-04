package com.xiaoaxiao.graduatemanagementsystem.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xiaoaxiao.graduatemanagementsystem.entity.Province

@Dao
interface ProvinceDao {

    @Insert
    fun insertProvince(province: Province)

    @Query("select * from Province")
    fun allProvinces(): List<Province>

    @Query("select * from Province where provinceName = :provinceName")
    fun findProvinceByName(provinceName: String): Array<Province>

    @Query("select * from Province where provinceId = :provinceId")
    fun findProvinceById(provinceId: Long): Array<Province>
}