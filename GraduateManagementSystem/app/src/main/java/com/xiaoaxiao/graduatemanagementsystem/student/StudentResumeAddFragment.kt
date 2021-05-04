package com.xiaoaxiao.graduatemanagementsystem.student

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.Utils.convertUriToPath
import com.xiaoaxiao.graduatemanagementsystem.dao.StudentDao
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Student
import kotlinx.android.synthetic.main.fragment_resume_add.*
import java.io.File
import java.net.URI

class StudentResumeAddFragment : Fragment() {

    val pdfResultCode = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_resume_add,container,false)

        val resumeAddButton: Button = mRootView.findViewById(R.id.add_resume_button)

        resumeAddButton.setOnClickListener {
            // 打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.setType("application/pdf")
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent,pdfResultCode)
        }
        return mRootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            pdfResultCode -> {
                // 0.第三方sdk可以通过uri的形式获取pdf
                // 1.直接将Uri对象保存在学生表中->通过TypeConverter进行转换
                // 2.根据Uri+第三方sdk，在app内部打开简历
                if (resultCode == Activity.RESULT_OK) {
                    val studentNumber = activity!!.intent.getStringExtra("student_number")!!
                    val studentDao = AppDatabase.getDatabase(context!!).studentDao()
                    val student = studentDao.findStudentByStudentNumber(studentNumber)[0]
                    student.resumeUri = data!!.data
                    studentDao.updateStudent(student)
                    Toast.makeText(context,"添加成功",Toast.LENGTH_LONG).show()
                    activity!!.onBackPressed()
                }
            }
        }
    }
}