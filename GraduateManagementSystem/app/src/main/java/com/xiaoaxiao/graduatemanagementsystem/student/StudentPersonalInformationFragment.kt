package com.xiaoaxiao.graduatemanagementsystem.student

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pdftron.pdf.controls.DocumentActivity
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase

/**
 * 学生个人页
 */
class StudentPersonalInformationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_personal_information,container,false)

        val studentNumber = activity!!.intent.getStringExtra("userNumber")!!

        val database = AppDatabase.getDatabase(context!!)
        val studentDao = database.studentDao()
        val collegeDao = database.collegeDao()
        val professionDao = database.professionDao()
        val provinceDao = database.provinceDao()

        val student = studentDao.findStudentByStudentNumber(studentNumber)[0]

        val studentNameText: TextView = mRootView.findViewById(R.id.student_name_text)
        studentNameText.text = student.studentName
        val studentNumberText: TextView = mRootView.findViewById(R.id.student_number_text)
        studentNumberText.text = student.studentNumber
        val studentCollegeText: TextView = mRootView.findViewById(R.id.student_college_text)
        studentCollegeText.text = collegeDao.findCollegeById(student.collegeId)[0].collegeName
        val studentProfessionText: TextView = mRootView.findViewById(R.id.student_profession_text)
        studentProfessionText.text = professionDao.findProfessionById(student.professionId)[0].professionName
        val studentProvinceText: TextView = mRootView.findViewById(R.id.student_province_text)
        studentProvinceText.text = provinceDao.findProvinceById(student.provinceId)[0].provinceName

        val studentResumeLayout: LinearLayout = mRootView.findViewById(R.id.student_resume_layout)
        val studentFavoriteLayout: LinearLayout = mRootView.findViewById(R.id.student_favorite_layout)
        val studentDeliveredLayout: LinearLayout = mRootView.findViewById(R.id.student_delivered_layout)

        studentResumeLayout.setOnClickListener {
            // 点击后要再查一次，否则用的是老数据
            val curStudent = studentDao.findStudentByStudentNumber(studentNumber)[0]
            val uri = curStudent.resumeUri

            if (uri == null) {
                val intent = Intent(context,StudentResumeActivity::class.java)
                intent.putExtra("student_number",studentNumber)
                startActivity(intent)
            } else {
                DocumentActivity.openDocument(context,uri)
            }
        }

        studentFavoriteLayout.setOnClickListener {
            val studentFavoriteJobDao = database.studentFavoriteDao()
            val studentFavoriteJobList = studentFavoriteJobDao.findFavoriteJobsByStudentId(student.studentId)
            if (studentFavoriteJobList.isEmpty()) {
                Toast.makeText(context,"暂无收藏职位",Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context,JobListActivity::class.java)
                intent.putExtra("student_number",studentNumber)
                intent.putExtra("content","favorite")
                startActivity(intent)
            }
        }

        studentDeliveredLayout.setOnClickListener {
            val studentDeliveredJobDao = database.studentDeliveredDao()
            val studentDeliveredJobList = studentDeliveredJobDao.findStudentDeliveredJobByStudentId(student.studentId)
            if (studentDeliveredJobList.isEmpty()) {
                Toast.makeText(context,"暂无投递职位",Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context,JobListActivity::class.java)
                intent.putExtra("student_number",studentNumber)
                intent.putExtra("content","delivered")
                startActivity(intent)
            }
        }

        return mRootView
    }
}