package com.xiaoaxiao.graduatemanagementsystem.teacher

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job
import com.xiaoaxiao.graduatemanagementsystem.student.JobDetailActivity

class TeacherJobAdapter(val jobList: MutableList<Job>, val context: Context): RecyclerView.Adapter<TeacherJobAdapter.JobViewHolder>() {

    inner class JobViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val jobName: TextView = view.findViewById(R.id.job_name)
        val jobNature: TextView = view.findViewById(R.id.job_nature)
        val jobSalary: TextView = view.findViewById(R.id.job_salary)
        val jobCompany: TextView = view.findViewById(R.id.job_company)
        val jobProvince: TextView = view.findViewById(R.id.job_province)
        val jobItemLayout: LinearLayout = view.findViewById(R.id.job_item_layout)
        val jobModifyButton: Button = view.findViewById(R.id.job_modify_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.teacher_job_item,parent,false)
        return JobViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val database = AppDatabase.getDatabase(context)
        val provinceDao = database.provinceDao()
        val curJob = jobList[position]
        holder.jobName.text = curJob.jobName
        holder.jobNature.text = curJob.jobNature
        holder.jobSalary.text = curJob.salary
        holder.jobCompany.text = curJob.company
        holder.jobProvince.text = provinceDao.findProvinceById(curJob.provinceId)[0].provinceName
        holder.jobItemLayout.setOnClickListener {
            val intent = Intent(context, JobDetailActivity::class.java)
            intent.putExtra("job",curJob)
            intent.putExtra("student_number","teacher")
            context.startActivity(intent)
        }
        holder.jobModifyButton.setOnClickListener {
            val intent = Intent(context,JobChangedActivity::class.java)
            intent.putExtra("change_type","modify")
            intent.putExtra("curJob",curJob)
            context.startActivity(intent)
        }
    }
}