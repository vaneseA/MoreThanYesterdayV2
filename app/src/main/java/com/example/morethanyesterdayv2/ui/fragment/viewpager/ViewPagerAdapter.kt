package com.example.morethanyesterdayv2.ui.fragment.viewpager


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.morethanyesterdayv2.ui.fragment.*

private const val NUM_TABS = 10

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        var getFragment:Fragment? = null
        when (position) {
            0 -> getFragment =  BackFragment()
            1 -> getFragment =  ChestFragment()
            2 -> getFragment =  BicepFragment()
            3 -> getFragment =  TricepsFragment()
            4 -> getFragment =  TrapeziusFragment()
            5 -> getFragment =  ShoulderFragment()
            6 -> getFragment =  LowerBodyFragment()
            7 -> getFragment =  AbsFragment()
            8 -> getFragment =  CardioFragment()
            9 -> getFragment =  CustomFragment()

        }
        return getFragment!!
    }
}