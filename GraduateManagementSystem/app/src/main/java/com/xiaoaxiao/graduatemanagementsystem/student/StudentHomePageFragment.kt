package com.xiaoaxiao.graduatemanagementsystem.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.xiaoaxiao.graduatemanagementsystem.R

class StudentHomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mRootView = inflater.inflate(R.layout.fragment_homepage, container, false)

        val tabLayout: TabLayout = mRootView.findViewById(R.id.tab_layout)
        val viewPager: ViewPager = mRootView.findViewById(R.id.view_pager)

        val fragments = mutableListOf<Fragment>()
        val titles = mutableListOf<String>()

        fragments.add(StudentWebsiteNewsFragment())
        fragments.add(StudentCareersGuidanceFragment())
        fragments.add(StudentPersonalInformationFragment())

        titles.add("网站新闻")
        titles.add("就业指导")
        titles.add("个人信息")

        // 配置ViewPager的adapter
        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            /**
             * 返回对应的Fragment
             */
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            /**
             * 返回子模块的数量
             */
            override fun getCount(): Int {
                return fragments.size
            }

            /**
             * 返回子模块的标题
             */
            override fun getPageTitle(position: Int): CharSequence? {
                return titles[position]
            }
        }

        tabLayout.setupWithViewPager(viewPager)

        return mRootView
    }
}