package com.xiaoaxiao.graduatemanagementsystem.student

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.R

class StudentMessageAdapter(val studentMessageList: List<StudentMessage>,val context: Context): RecyclerView.Adapter<StudentMessageAdapter.StudentMessageViewHolder>() {

    inner class StudentMessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val studentName: TextView = view.findViewById(R.id.student_name)
        val studentCollegeName: TextView = view.findViewById(R.id.student_college_name)
        val studentProfessionName: TextView = view.findViewById(R.id.student_profession_name)
        val studentJobNature: TextView = view.findViewById(R.id.student_job_nature)
        val studentJobCompany: TextView = view.findViewById(R.id.student_job_company)
        val studentJobProvince: TextView = view.findViewById(R.id.student_job_province)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentMessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_message_item,parent,false)
        return StudentMessageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentMessageList.size
    }

    override fun onBindViewHolder(holder: StudentMessageViewHolder, position: Int) {
        val curStudentMessage = studentMessageList[position]
        holder.studentName.text = curStudentMessage.studentName
        holder.studentCollegeName.text = curStudentMessage.studentCollegeName
        holder.studentProfessionName.text = curStudentMessage.studentProfessionName
        holder.studentJobNature.text = curStudentMessage.studentJobNature
        holder.studentJobCompany.text = curStudentMessage.studentJobCompany
        holder.studentJobProvince.text = curStudentMessage.studentJobProvince
    }
}