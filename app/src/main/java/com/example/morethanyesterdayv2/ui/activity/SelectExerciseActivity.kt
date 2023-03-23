package com.example.morethanyesterdayv2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.viewmodel.ExerciseData

class SelectExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectExerciseBinding
    lateinit var exerciseListAdapter: ExerciseListAdapter
    val datas = mutableListOf<ExerciseData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }
    private fun initRecycler() {
        exerciseListAdapter = ExerciseListAdapter(this)
        binding.ExerciseRV.adapter = exerciseListAdapter


        datas.apply {
                add(ExerciseData(name = "데드리프트", type = "등"))
                add(ExerciseData(name = "풀업", type = "등"))
                add(ExerciseData(name = "렛풀다운", type = "등"))
                add(ExerciseData(name = "케이블 시티드 로우", type = "등"))
            exerciseListAdapter.datas = datas
            exerciseListAdapter.notifyDataSetChanged()

        }
        exerciseListAdapter.setOnItemClickListener(object : ExerciseListAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ExerciseData, pos : Int) {

            }

        })
    }
}