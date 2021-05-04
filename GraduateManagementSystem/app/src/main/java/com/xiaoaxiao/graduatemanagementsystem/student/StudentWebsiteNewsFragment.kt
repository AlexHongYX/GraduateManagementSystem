package com.xiaoaxiao.graduatemanagementsystem.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.JobAdapter
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job

/**
 * 岗位列表
 */
class StudentWebsiteNewsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news,container,false)

        val jobsRecyclerView = view.findViewById<RecyclerView>(R.id.jobs_recycler_view)

        // 将RecyclerView中子项的布局交给layoutManager来做，而不需要自己设计(ListView)
        val layoutManager = LinearLayoutManager(activity)
        jobsRecyclerView.layoutManager = layoutManager

        val studentNumber = activity!!.intent.getStringExtra("userNumber")!!
        val jobList = initJobList()
        // 设置RecyclerView的适配器
        jobsRecyclerView.adapter =
            JobAdapter(jobList,studentNumber,context!!)

        // 添加Android自带的分割线
        jobsRecyclerView.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))

        return view
    }

    private fun initJobList() : List<Job> = AppDatabase.getDatabase(context!!).jobDao().allJobs()
}