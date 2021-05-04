package com.xiaoaxiao.graduatemanagementsystem.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoaxiao.graduatemanagementsystem.R

class TeacherEmploymentMessageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_employment_message,container,false)
        return mRootView
    }
}