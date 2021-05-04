package com.xiaoaxiao.graduatemanagementsystem.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pdftron.pdf.controls.DocumentActivity
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase

/**
 * 用于展示学生简历：
 *  有则显示，无则添加
 */
class StudentResumeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_resume)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.resume_container,
                StudentResumeAddFragment()
            ).commit()
    }
}