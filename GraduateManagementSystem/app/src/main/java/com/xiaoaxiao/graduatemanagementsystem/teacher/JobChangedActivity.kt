package com.xiaoaxiao.graduatemanagementsystem.teacher

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.xiaoaxiao.graduatemanagementsystem.R
import com.xiaoaxiao.graduatemanagementsystem.database.AppDatabase
import com.xiaoaxiao.graduatemanagementsystem.entity.Job
import kotlinx.android.synthetic.main.activity_job_changed.*

class JobChangedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_changed)

        val type = intent.getStringExtra("change_type")!!

        val database = AppDatabase.getDatabase(this)

        if (type == "modify") {

            val curJob: Job = intent.getSerializableExtra("curJob") as Job
            val provinceDao = database.provinceDao()
            val jobDao = database.jobDao()
            val allProvinces = provinceDao.allProvinces()
            var position = 0
            var provinceName = ""
            val provinceNameList = mutableListOf<String>()
            allProvinces.forEachIndexed { index, province ->
                if (province.provinceId == curJob.provinceId) {
                    position = index
                    provinceName = provinceDao.findProvinceById(curJob.provinceId)[0].provinceName
                }
                provinceNameList.add(province.provinceName)
            }
            job_name_detail_edit.setText(curJob.jobName)
            job_nature_detail_edit.setText(curJob.jobNature)
            job_company_detail_edit.setText(curJob.company)
            job_salary_detail_edit.setText(curJob.salary)
            job_introduction_detail_edit.setText(curJob.jobIntroduction)


            val provinceAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,provinceNameList)
            job_province_detail_spinner.adapter = provinceAdapter
            job_province_detail_spinner.setSelection(position)
            job_province_detail_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    provinceName = provinceAdapter.getItem(position)!!
                }
            }
            job_changed_button.text = "点击修改"
            job_changed_button.setOnClickListener {
                val newJob = Job(
                    jobId = curJob.jobId,
                    jobName = job_name_detail_edit.text.toString(),
                    jobNature = job_nature_detail_edit.text.toString(),
                    company = job_company_detail_edit.text.toString(),
                    salary = job_salary_detail_edit.text.toString(),
                    provinceId = provinceDao.findProvinceByName(provinceName)[0].provinceId,
                    jobIntroduction = job_introduction_detail_edit.text.toString()
                )
                jobDao.updateJob(newJob)
                Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}