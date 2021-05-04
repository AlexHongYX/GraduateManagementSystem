package com.xiaoaxiao.graduatemanagementsystem.student

/**
 * 用于显示学生信息->和Student不同，该类表示学生部分信息+工作部分信息，用来填充RecyclerView的Item
 */
data class StudentMessage (
    val studentName: String,
    val studentCollegeName: String,
    val studentProfessionName: String,
    val studentJobNature: String,
    val studentJobCompany: String,
    val studentJobProvince: String
)