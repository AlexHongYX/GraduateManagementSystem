package com.xiaoaxiao.graduatemanagementsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.xiaoaxiao.graduatemanagementsystem.Utils.EditTextClearUtil
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.User

class RegisterActivity : AppCompatActivity() {

    var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userNumberEditText = findViewById<EditText>(R.id.et_register_user_number)
        val passwordEditText = findViewById<EditText>(R.id.et_register_password)

        val userClearIcon = findViewById<ImageView>(R.id.register_user_number_clear)
        val passwordClearIcon = findViewById<ImageView>(R.id.register_password_clear)

        EditTextClearUtil.addClearListener(userNumberEditText,userClearIcon)
        EditTextClearUtil.addClearListener(passwordEditText,passwordClearIcon)

        val registerIcon = findViewById<Button>(R.id.register_user_btn)
        registerIcon.setOnClickListener {
            val userTypeClicked = userType
            val userNumber = userNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (userTypeClicked == null || userNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"请正确填写表单信息",Toast.LENGTH_LONG).show()
            } else if (!isBelongSchool(userTypeClicked,userNumber,this)) {
                Toast.makeText(this,"非本校人士不得进入",Toast.LENGTH_LONG).show()
            } else if (isRegistered(userNumber,this)) {
                Toast.makeText(this,"该账号已注册，不可重复注册",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show()
                val userDao = AppDatabase.getDatabase(this).userDao()
                val registerUser = User(
                    type = userTypeClicked,
                    userNumber = userNumber,
                    password = password
                )
                userDao.insertUser(registerUser)
                val loginIntent = Intent(this,LoginActivity::class.java)
                startActivity(loginIntent)
            }


        }
    }

    // 两个RadioButton点击事件
    fun registerOnRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.register_student_icon -> {
                    if (checked) {
                        userType = "Student"
                    }
                }
                R.id.register_teacher_icon -> {
                    if (checked) {
                        userType = "Teacher"
                    }
                }
            }
        }
    }

    companion object {
        /**
         * 判断是否属于本校学生/老师
         *  只有本校学生/老师才有权进入该管理系统
         */
        fun isBelongSchool(userType: String,userNumber: String,context: Context): Boolean {
            return if (userType == "Student") {
                val studentDao = AppDatabase.getDatabase(context).studentDao()
                studentDao.findStudentByStudentNumber(userNumber).isNotEmpty()
            } else {
                val teacherDao = AppDatabase.getDatabase(context).teacherDao()
                teacherDao.findTeacherByTeacherNumber(userNumber).isNotEmpty()
            }
        }

        /**
         * 是否已注册过
         */
        fun isRegistered(userNumber: String,context: Context): Boolean{
            val userDao = AppDatabase.getDatabase(context).userDao()
            return userDao.findUserFromUserNumber(userNumber).isNotEmpty()
        }
    }

}