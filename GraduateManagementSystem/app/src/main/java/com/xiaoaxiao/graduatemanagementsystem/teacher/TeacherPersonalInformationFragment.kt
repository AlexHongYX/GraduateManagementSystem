package com.xiaoaxiao.graduatemanagementsystem.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase

class TeacherPersonalInformationFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_teacher_personal_information,container,false)

        val teacherNumber = activity!!.intent.getStringExtra("userNumber")!!

        val database = AppDatabase.getDatabase(context!!)
        val teacherDao = database.teacherDao()
        val collegeDao = database.collegeDao()
        val professionDao = database.professionDao()
        
        val teacher = teacherDao.findTeacherByTeacherNumber(teacherNumber)[0]

        val teacherNameText: TextView = mRootView.findViewById(R.id.teacher_name_text)
        teacherNameText.text = teacher.teacherName
        val teacherSexText: TextView = mRootView.findViewById(R.id.teacher_sex_text)
        teacherSexText.text = teacher.sex
        val teacherNumberText: TextView = mRootView.findViewById(R.id.teacher_number_text)
        teacherNumberText.text = teacher.teacherNumber
        val teacherCollegeText: TextView = mRootView.findViewById(R.id.teacher_college_text)
        teacherCollegeText.text = collegeDao.findCollegeById(teacher.collegeId)[0].collegeName
        val teacherProfessionText: TextView = mRootView.findViewById(R.id.teacher_profession_text)
        teacherProfessionText.text = professionDao.findProfessionById(teacher.professionId)[0].professionName

        return mRootView
    }
}