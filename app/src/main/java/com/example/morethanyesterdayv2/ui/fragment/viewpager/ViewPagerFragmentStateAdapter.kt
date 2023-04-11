package com.example.morethanyesterdayv2.ui.fragment.viewpager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.morethanyesterdayv2.ui.fragment.*

class ViewPagerFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    // ViewPager에서 이동할 Fragment list
    private var fragments: ArrayList<Fragment> = arrayListOf(
        BackFragment(),
        ChestFragment(),
        AbsFragment(),
        CardioFragment(),
        BicepFragment(),
        LowerBodyFragment(),
        ShoulderFragment(),
        TrapeziusFragment(),
        TricepsFragment(),
        CustomFragment()

    )

    // FragmentStateAdapter 상속 시 무조건 override 해야하는 fun
    override fun getItemCount(): Int {
        return fragments.size
    }

    // FragmentStateAdapter 상속 시 무조건 override 해야하는 fun (View Pager의 position에 해당하는 fragment return)
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}