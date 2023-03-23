package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.databinding.ActivitySelectExerciseBinding
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import com.example.morethanyesterdayv2.ui.adapter.ProfileAdapter
import com.example.morethanyesterdayv2.viewmodel.ProfileData

class SelectExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectExerciseBinding
    lateinit var profileAdapter: ProfileAdapter
    val datas = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }
    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)
        binding.ExerciseRV.adapter = profileAdapter


        datas.apply {
                add(ProfileData(name = "데드리프트", type = "등"))
                add(ProfileData(name = "풀업", type = "등"))
                add(ProfileData(name = "렛풀다운", type = "등"))
                add(ProfileData(name = "케이블 시티드 로우", type = "등"))
            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()

        }
        profileAdapter.setOnItemClickListener(object : ProfileAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ProfileData, pos : Int) {

            }

        })
    }
}