package com.xiaoaxiao.graduatemanagementsystem.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job

/**
 * 展示工作岗位的list
 *  收藏的岗位list/已投递的岗位list
 */
class JobListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        initView()
    }

    private fun initView() {
        val jobsRecyclerView: RecyclerView = this.findViewById(R.id.jobs_list_recycler_view)

        val layoutManager = LinearLayoutManager(this)
        jobsRecyclerView.layoutManager = layoutManager

        val studentNumber = intent.getStringExtra("student_number").toString()
        val content = intent.getStringExtra("content")

        val database = AppDatabase.getDatabase(this)

        val studentDao = database.studentDao()

        val studentId = studentDao.findStudentByStudentNumber(studentNumber)[0].studentId

        val jobIdList: MutableList<Long> = mutableListOf()
        if (content == "favorite") {
            val studentFavoriteJobDao = database.studentFavoriteDao()
            val studentFavoriteJobList = studentFavoriteJobDao.findFavoriteJobsByStudentId(studentId)

            studentFavoriteJobList.forEach {
                jobIdList.add(it.jobId)
            }


        } else {
            val studentDeliveredJobDao = database.studentDeliveredDao()
            val studentDeliveredJobList = studentDeliveredJobDao.findStudentDeliveredJobByStudentId(studentId)


            studentDeliveredJobList.forEach {
                jobIdList.add(it.jobId)
            }
        }

        val jobDao = database.jobDao()

        val jobList:MutableList<Job> = mutableListOf()
        jobIdList.forEach {
            val job = jobDao.findJobById(it)[0]
            jobList.add(job)
        }

        jobsRecyclerView.adapter =
            StudentJobAdapter(
                jobList,
                studentNumber,
                this
            )

        jobsRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}