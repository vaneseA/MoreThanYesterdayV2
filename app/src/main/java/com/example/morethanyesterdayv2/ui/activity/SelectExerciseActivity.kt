package com.example.morethanyesterdayv2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        initRecycler()
    }

    private fun initRecycler() {
        exerciseListAdapter = ExerciseListAdapter(this)
        binding.ExerciseRV.adapter = exerciseListAdapter

        viewModel.exerciseDataList.observe(this) { ExerciseEntity ->
            exerciseListAdapter.datas = ExerciseEntity
            exerciseListAdapter.notifyDataSetChanged()
        }
    }

}