package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding

class SelectedDateActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedDateBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.SelectExerciseBtn.setOnClickListener {
            startActivity(Intent(this, SelectExerciseActivity::class.java))
        }
    }
}