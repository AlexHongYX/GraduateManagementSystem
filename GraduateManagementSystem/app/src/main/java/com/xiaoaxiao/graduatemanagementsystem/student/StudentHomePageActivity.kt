package com.xiaoaxiao.graduatemanagementsystem.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoaxiao.graduatemanagementsystem.R

class StudentHomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                StudentHomePageFragment()
            ).commit()
    }
}