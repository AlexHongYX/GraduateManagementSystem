package com.xiaoaxiao.graduatemanagementsystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.xiaoaxiao.graduatemanagementsystem.Utils.EditTextClearUtil
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.student.StudentHomePageActivity
import com.xiaoaxiao.graduatemanagementsystem.teacher.TeacherHomePageActivity

class LoginActivity : AppCompatActivity() {

    var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AppDatabase.getDatabase(this)
        initView()
    }

    private fun initView() {
        val userNumberEditText = findViewById<EditText>(R.id.et_login_user_number)
        val passwordEditText = findViewById<EditText>(R.id.et_login_password)

        val userClearIcon = findViewById<ImageView>(R.id.login_user_number_clear)
        val passwordClearIcon = findViewById<ImageView>(R.id.login_password_clear)

        EditTextClearUtil.addClearListener(userNumberEditText,userClearIcon)
        EditTextClearUtil.addClearListener(passwordEditText,passwordClearIcon)

        val loginIcon = findViewById<Button>(R.id.login_btn)
        val registerIcon = findViewById<Button>(R.id.register_btn)

        val registerIntent = Intent(this,RegisterActivity::class.java)
        registerIcon.setOnClickListener {
            startActivity(registerIntent)
        }

        loginIcon.setOnClickListener {
            val userTypeClicked = userType
            val userNumber = userNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (userTypeClicked == null || userNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"请正确填写表单信息",Toast.LENGTH_LONG).show()
            } else if (!RegisterActivity.isBelongSchool(userTypeClicked, userNumber, this)) {
                Toast.makeText(this,"非本校人士不得进入",Toast.LENGTH_LONG).show()
            } else if (!RegisterActivity.isRegistered(userNumber, this)) {
                Toast.makeText(this,"该账号未注册，请先注册",Toast.LENGTH_LONG).show()
            } else if (isAccessible(userNumber,password,this)) {
                Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show()
                val homepageIntent = if (userTypeClicked == "Student") {
                    Intent(this,
                        StudentHomePageActivity::class.java)
                } else {
                    Intent(this,
                        TeacherHomePageActivity::class.java)
                }
                homepageIntent.putExtra("userType",userTypeClicked)
                homepageIntent.putExtra("userNumber",userNumber)
                homepageIntent.putExtra("password",password)
                startActivity(homepageIntent)
            } else {
                Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_LONG).show()
            }
        }

    }

    /**
     * 是否可进入，包含用户名密码等信息
     */
    private fun isAccessible(userNumber:String, password: String, context: Context): Boolean {
        val userDao = AppDatabase.getDatabase(context).userDao()
        return userDao.findUserFromUserNumberAndPassword(userNumber,password).isNotEmpty()
    }

    // 两个RadioButton点击事件
    fun loginOnRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.login_student_icon -> {
                    if (checked) {
                        userType = "Student"
                    }
                }
                R.id.login_teacher_icon -> {
                    if (checked) {
                        userType = "Teacher"
                    }
                }
            }
        }
    }

}