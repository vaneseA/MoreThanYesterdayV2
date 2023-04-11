package com.example.morethanyesterdayv2.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.ui.fragment.viewpager.ViewPagerAdapter
import com.example.morethanyesterdayv2.ui.fragment.viewpager.ViewPagerFragmentStateAdapter
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
    private lateinit var exerciseListAdapter: ExerciseListAdapter

    private val viewModel: SelectExerciseViewModel by viewModels()

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

        initRecycler()
    }

    private fun initRecycler() {
        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(this, selectedDate)


        viewModel.exerciseDataList.observe(this) { ExerciseEntity ->
            exerciseListAdapter.exerciseDataList = ExerciseEntity
            exerciseListAdapter.notifyDataSetChanged()
        }
    }

}