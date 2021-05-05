package com.xiaoaxiao.graduatemanagementsystem.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase

class TeacherJobNewsFragment: Fragment() {

    var mRootView: View? = null
    var teacherJobsRecyclerView: RecyclerView? = null

    override fun onStart() {
        super.onStart()

        val jobList = AppDatabase.getDatabase(context!!).jobDao().allJobs()
        teacherJobsRecyclerView!!.adapter =
            TeacherJobAdapter(
                jobList.toMutableList(),
                context!!
            )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_teacher_job_news,container,false)

        teacherJobsRecyclerView = mRootView!!.findViewById(R.id.teacher_jobs_recycler_view)

        val layoutManager = LinearLayoutManager(activity)
        teacherJobsRecyclerView!!.layoutManager = layoutManager

        // 添加Android自带的分割线
        teacherJobsRecyclerView!!.addItemDecoration(
            DividerItemDecoration(activity,
                DividerItemDecoration.VERTICAL)
        )
        return mRootView
    }
}