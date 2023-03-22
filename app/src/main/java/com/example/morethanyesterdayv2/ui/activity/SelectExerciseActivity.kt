package com.example.morethanyesterdayv2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseAdapter
import com.example.morethanyesterdayv2.viewmodel.ExerciseModel

class SelectExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectExerciseBinding
    var datas = mutableListOf<ExerciseModel>()
    lateinit var exerciseAdapter: ExerciseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

    }

    private fun initRecycler() {
        exerciseAdapter = ExerciseAdapter(this)
        binding.ExerciseRV.adapter = exerciseAdapter


        datas.apply {
            add(ExerciseModel(name = "데드리프트", type = "등"))
            add(ExerciseModel(name = "덤벨프레스", type = "가슴"))
            add(ExerciseModel(name = "데드리프트", type = "등"))
            add(ExerciseModel(name = "데드리프트", type = "등"))
            add(ExerciseModel(name = "데드리프트", type = "등"))
            add(ExerciseModel(name = "데드리프트", type = "등"))
            add(ExerciseModel(name = "데드리프트", type = "등"))

            exerciseAdapter.datas = datas
            exerciseAdapter.notifyDataSetChanged()
        }
    }
}