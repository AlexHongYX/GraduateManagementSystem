package com.xiaoaxiao.graduatemanagementsystem

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job
import com.xiaoaxiao.graduatemanagementsystem.entity.StudentFavoriteJob
import com.xiaoaxiao.graduatemanagementsystem.student.JobDetailActivity

class JobAdapter(val jobList: List<Job>, val studentNumber: String,val context: Context) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    var mView: View? = null

    /**
     * 实际上JobViewHolder仅仅只是news_item.xml最外层布局View的缓存
     *      使用JobViewHolder.xxx避免每次都是用findViewById()找布局中的某个控件
     */
    inner class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jobName: TextView = view.findViewById(R.id.job_name)
        val jobNature: TextView = view.findViewById(R.id.job_nature)
        val jobSalary: TextView = view.findViewById(R.id.job_salary)
        val jobCompany: TextView = view.findViewById(R.id.job_company)
        val jobProvince: TextView = view.findViewById(R.id.job_province)
        val jobItemLayout: LinearLayout = view.findViewById(R.id.job_item_layout)
        val jobDemand: ImageView = view.findViewById(R.id.job_demand)
    }

    /**
     * 将子项View与自定义ViewHolder进行绑定，并返回ViewHolder对象(缓存)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_item,parent,false)

        mView = view

        return JobViewHolder(view)
    }

    /**
     * 返回子项的数量：由外部传入数据控制
     */
    override fun getItemCount(): Int {
        return jobList.size
    }

    /**
     * 绑定ViewHolder：通过外部传入数据渲染ViewHolder(子项View)的控件
     */
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
            val intent = Intent(context,JobDetailActivity::class.java)
            intent.putExtra("job",curJob)
            intent.putExtra("student_number",studentNumber)
            context.startActivity(intent)
        }
        val studentDao = database.studentDao()
        val studentId = studentDao.findStudentByStudentNumber(studentNumber)[0].studentId
        val studentFavoriteJobDao = AppDatabase.getDatabase(context).studentFavoriteDao()
        val favoriteJobList = studentFavoriteJobDao.findFavoriteJobsByStudentId(studentId)
        val favoriteJobIdList = mutableListOf<Long>()
        favoriteJobList.forEach {
            favoriteJobIdList.add(it.jobId)
        }

        if (favoriteJobIdList.contains(curJob.jobId)) {
            holder.jobDemand.setImageDrawable(mView!!.resources.getDrawable(R.drawable.apple_pic))
        } else {
            holder.jobDemand.setImageDrawable(mView!!.resources.getDrawable(R.drawable.picture))
        }
        holder.jobDemand.setOnClickListener {
            val clickFavoriteJobList = studentFavoriteJobDao.findFavoriteJobsByStudentId(studentId)
            val clickFavoriteJobIdList = mutableListOf<Long>()
            clickFavoriteJobList.forEach {
                clickFavoriteJobIdList.add(it.jobId)
            }
            val isFavorited = clickFavoriteJobIdList.contains(curJob.jobId)
            if (isFavorited) {
                studentFavoriteJobDao.deleteJob(studentId,curJob.jobId)
                Toast.makeText(context,"已取消",Toast.LENGTH_LONG).show()
                holder.jobDemand.setImageDrawable(mView!!.resources.getDrawable(R.drawable.picture))
            } else {
                studentFavoriteJobDao.insertStudentFavoriteJob(StudentFavoriteJob(jobId = curJob.jobId,studentId = studentId))
                Toast.makeText(context,"收藏成功",Toast.LENGTH_LONG).show()
                holder.jobDemand.setImageDrawable(mView!!.resources.getDrawable(R.drawable.apple_pic))
            }

        }
    }
}