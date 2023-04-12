package com.example.morethanyesterdayv2.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.ui.fragment.*
import com.example.morethanyesterdayv2.ui.fragment.viewpager.ViewPagerAdapter
import com.example.morethanyesterdayv2.ui.fragment.viewpager.ViewPagerFragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SelectExerciseActivity : AppCompatActivity() {

    private val tabTitleArray = arrayOf(
        "가슴",
        "등",
        "이두",
        "삼두",
        "승모근",
        "어깨",
        "하체",
        "복근",
        "유산소",
        "커스텀"
    )

    private lateinit var binding: ActivitySelectExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        Log.d("selectedDate", selectedDate)
        supportActionBar?.title = selectedDate

        val absFragment = AbsFragment.newInstance(selectedDate)
        val chestFragment = ChestFragment.newInstance(selectedDate)
        val backFragment = BackFragment.newInstance(selectedDate)
        val bicepFragment = BicepFragment.newInstance(selectedDate)
        val cardioFragment = CardioFragment.newInstance(selectedDate)
        val lowerBodyFragment = LowerBodyFragment.newInstance(selectedDate)
        val shoulderFragment = ShoulderFragment.newInstance(selectedDate)
        val trapeziusFragment = TrapeziusFragment.newInstance(selectedDate)
        val tricepsFragment = TricepsFragment.newInstance(selectedDate)
        val customFragment = CustomFragment.newInstance(selectedDate)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, chestFragment, "CHEST_FRAGMENT_TAG")
            .commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, chestFragment, "CHEST_FRAGMENT_TAG")
                            .commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, backFragment, "BACK_FRAGMENT_TAG")
                            .commit()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, bicepFragment, "BICEP_FRAGMENT_TAG")
                            .commit()
                    }
                    3 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, tricepsFragment, "TRICEPS_FRAGMENT_TAG")
                            .commit()
                    }
                    4 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, trapeziusFragment, "TRAPEZIUS_FRAGMENT_TAG")
                            .commit()
                    }
                    5 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, shoulderFragment, "SHOULDER_FRAGMENT_TAG")
                            .commit()
                    }
                    6 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, lowerBodyFragment, "LOWER_BODY_FRAGMENT_TAG")
                            .commit()
                    }
                    7 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, absFragment, "ABS_FRAGMENT_TAG")
                            .commit()
                    }
                    8 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, cardioFragment, "CARDIO_FRAGMENT_TAG")
                            .commit()
                    }
                    9 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, customFragment, "CUSTOM_FRAGMENT_TAG")
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Do nothing
            }
        })
    }


}
