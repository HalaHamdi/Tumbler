package com.example.tumbler.userprofile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm:FragmentManager,val fragmentCount:Int) : FragmentStatePagerAdapter(fm) {

    private val fragmentTitleList = mutableListOf("Posts","Likes","Following")

    override fun getItem(position:Int): Fragment {

        when (position) {
            0 -> return PostsFragment()
            1 -> return LikesFragment()
            2 -> return FollowingFragment()
            else -> return PostsFragment()
        }
    }
    override fun getPageTitle(position: Int):CharSequence?{
        return fragmentTitleList[position]
    }
    override fun getCount(): Int = fragmentCount
}
