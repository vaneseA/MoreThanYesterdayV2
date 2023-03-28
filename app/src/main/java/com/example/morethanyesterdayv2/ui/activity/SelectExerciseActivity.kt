package com.example.morethanyesterdayv2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter


class SelectExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectExerciseBinding
    private lateinit var exerciseListAdapter: ExerciseListAdapter

    private val viewModel: SelectExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        Log.d("selectedDate", selectedDate)
        supportActionBar?.title = selectedDate

        initRecycler()
    }

    private fun initRecycler() {
        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(this, selectedDate)
        binding.ExerciseRV.adapter = exerciseListAdapter

        viewModel.exerciseDataList.observe(this) { ExerciseEntity ->
            exerciseListAdapter.datas = ExerciseEntity
            exerciseListAdapter.notifyDataSetChanged()
        }
    }

}