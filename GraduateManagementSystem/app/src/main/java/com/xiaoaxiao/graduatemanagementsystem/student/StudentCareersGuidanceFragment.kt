package com.xiaoaxiao.graduatemanagementsystem.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase

/**
 * 学生信息页
 */
class StudentCareersGuidanceFragment : Fragment() {

    private var isFirst = true
    private var mRecyclerView: RecyclerView? = null
    private var studentMessageList: MutableList<StudentMessage>? = null

    private var selectedCollegeName: String = "学院"
    private var selectedJobNatureName: String = "岗位"
    private var selectedJobProvicne: String = "省份"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_careers,container,false)

        val database = AppDatabase.getDatabase(context!!)
        val collegeDao = database.collegeDao()
        val jobDao = database.jobDao()
        val provinceDao = database.provinceDao()

        val collegeList = collegeDao.allColleges()
        val collegeNameList = mutableListOf<String>()
        collegeNameList.add("学院")
        collegeList.forEach {
            collegeNameList.add(it.collegeName)
        }

        val jobList = jobDao.allJobs()
        val jobNatureSet = mutableSetOf<String>()
        jobNatureSet.add("岗位")
        val jobProvinceSet = mutableSetOf<String>()
        jobProvinceSet.add("省份")
        jobList.forEach {
            jobNatureSet.add(it.jobNature)
            jobProvinceSet.add(provinceDao.findProvinceById(it.provinceId)[0].provinceName)
        }

        val collegeSpinner: Spinner = mRootView.findViewById(R.id.college_spinner)
        val jobNatureSpinner: Spinner = mRootView.findViewById(R.id.job_nature_spinner)
        val jobProvinceSpinner: Spinner = mRootView.findViewById(R.id.job_province_spinner)

        // 1.表格总布局使用水平排列的TextView+RecyclerView组成
        // 2.RecyclerView中Item的布局同样使用水平排列的TextView，格式与上面的TextView相同
        // 3.获取数据表中数据，填充RecyclerView
        mRecyclerView = mRootView.findViewById(R.id.students_recycler_view)
        val layoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = layoutManager

        val studentOfferedJobDao = database.studentOfferedDao()
        val studentDao = database.studentDao()
        val professionDao = database.professionDao()

        val studentOfferedJobList = studentOfferedJobDao.allStudentOfferedJobs()
        studentMessageList = mutableListOf<StudentMessage>()
        studentOfferedJobList.forEach {
            val student = studentDao.findStudentByStudentId(it.studentId)[0]
            val job = jobDao.findJobById(it.jobId)[0]
            val collegeName = collegeDao.findCollegeById(student.collegeId)[0].collegeName
            val professionName = professionDao.findProfessionById(student.professionId)[0].professionName
            val provinceName = provinceDao.findProvinceById(job.provinceId)[0].provinceName
            val studentMessage = StudentMessage(
                studentName = student.studentName,
                studentCollegeName = collegeName,
                studentProfessionName = professionName,
                studentJobNature = job.jobNature,
                studentJobCompany = job.company,
                studentJobProvince = provinceName
            )
            studentMessageList!!.add(studentMessage)
        }

        mRecyclerView!!.adapter = StudentMessageAdapter(studentMessageList!!,context!!)

        mRecyclerView!!.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))

        val collegeAdapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,collegeNameList)
        collegeSpinner.adapter = collegeAdapter
        collegeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCollegeName = collegeAdapter.getItem(position)!!
            }
        }

        val jobNatureAdapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,jobNatureSet.toList())
        jobNatureSpinner.adapter = jobNatureAdapter
        jobNatureSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedJobNatureName = jobNatureAdapter.getItem(position)!!
            }
        }

        val jobProvinceAdapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,jobProvinceSet.toList())
        jobProvinceSpinner.adapter = jobProvinceAdapter
        jobProvinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedJobProvicne = jobProvinceAdapter.getItem(position)!!
            }
        }

        val studentSearchButton: Button = mRootView.findViewById(R.id.student_search_button)
        studentSearchButton.setOnClickListener {
            studentMessageList = mutableListOf<StudentMessage>()
            studentOfferedJobList.forEach {
                val student = studentDao.findStudentByStudentId(it.studentId)[0]
                val job = jobDao.findJobById(it.jobId)[0]
                val collegeName = collegeDao.findCollegeById(student.collegeId)[0].collegeName
                val professionName = professionDao.findProfessionById(student.professionId)[0].professionName
                val jobNatureName = job.jobNature
                val provinceName = provinceDao.findProvinceById(job.provinceId)[0].provinceName
                if ((selectedCollegeName == collegeName || selectedCollegeName == "学院")
                    && (selectedJobNatureName == jobNatureName || selectedJobNatureName == "岗位")
                    && (selectedJobProvicne == provinceName || selectedJobProvicne == "省份")) {
                    val studentMessage = StudentMessage(
                        studentName = student.studentName,
                        studentCollegeName = collegeName,
                        studentProfessionName = professionName,
                        studentJobNature = jobNatureName,
                        studentJobCompany = job.company,
                        studentJobProvince = provinceName
                    )
                    studentMessageList!!.add(studentMessage)
                }
            }
            mRecyclerView!!.adapter = StudentMessageAdapter(studentMessageList!!,context!!)
            if (studentMessageList!!.isEmpty()) {
                Toast.makeText(context,"无结果",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context,"查询成功",Toast.LENGTH_SHORT).show()
            }
        }

        return mRootView
    }
}
