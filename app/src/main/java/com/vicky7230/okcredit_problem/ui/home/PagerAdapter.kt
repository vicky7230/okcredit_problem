package com.vicky7230.okcredit_problem.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.vicky7230.okcredit_problem.ui.news.NewsFragment


class PagerAdapter(
    private val sources: MutableList<String>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val fragment = NewsFragment()
        fragment.arguments = Bundle().apply {
            putString(NewsFragment.SOURCE, sources[position])
        }
        return fragment
    }


    override fun getCount(): Int {
        return sources.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return sources[position]
    }
}