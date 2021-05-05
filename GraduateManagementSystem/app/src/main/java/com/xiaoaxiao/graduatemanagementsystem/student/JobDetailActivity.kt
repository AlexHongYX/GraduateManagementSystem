package com.xiaoaxiao.graduatemanagementsystem.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job
import com.xiaoaxiao.graduatemanagementsystem.entity.StudentDeliveredJob
import kotlinx.android.synthetic.main.activity_job_detail.*

class JobDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        val database = AppDatabase.getDatabase(this)
        val provinceDao = database.provinceDao()

        val studentNumber = intent.getStringExtra("student_number")!!

        val curJob = intent.getSerializableExtra("job") as Job
        job_name_detail.text = curJob.jobName
        job_nature_detail.text = curJob.jobNature
        job_company_detail.text = curJob.company
        job_salary_detail.text = curJob.salary

        job_province_detail.text = provinceDao.findProvinceById(curJob.provinceId)[0].provinceName
        job_introduction_detail.text = curJob.jobIntroduction


        if (studentNumber != "teacher") {
            // 1.看当前岗位当前学生是否已经投递
            val studentDao = database.studentDao()
            val studentId = studentDao.findStudentByStudentNumber(studentNumber)[0].studentId

            val studentDeliveredJobDao = database.studentDeliveredDao()
            val studentDeliveredJobArray = studentDeliveredJobDao.findStudentDeliveredJob(studentId,curJob.jobId)
            if (studentDeliveredJobArray.isEmpty()) {
                // 2.若未投递则可点击按钮投递
                job_deliver_button.text = "点击投递"
                // TODO hongyangxiao 判断是否需要上传简历 1.直接唤起上传页面 2.提示需要在个人页上传简历
                job_deliver_button.setOnClickListener {
                    studentDeliveredJobDao.insertStudentDeliveredJob(
                        StudentDeliveredJob(
                            studentId = studentId,
                            jobId = curJob.jobId
                        )
                    )
                    Toast.makeText(this,"投递成功",Toast.LENGTH_LONG).show()
                    job_deliver_button.isClickable = false
                    job_deliver_button.text = "已投递"
                }
            } else {
                // 3.若已投递，则按钮非可选状态
                job_deliver_button.setOnClickListener {
                    Toast.makeText(this,"不可重复投递",Toast.LENGTH_LONG).show()
                }
                job_deliver_button.text = "已投递"
            }
        } else {
            job_deliver_button.visibility = View.GONE
        }

    }
}