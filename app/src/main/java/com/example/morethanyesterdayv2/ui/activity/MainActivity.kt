package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.morethanyesterdayv2.databinding.ActivityMainBinding
import com.example.morethanyesterdayv2.viewmodel.MainViewModel
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel // ViewModel 객체 선언

    var userID: String = "userID"
    lateinit var fname: String
    lateinit var str: String
    lateinit var updateBtn: Button
    lateinit var deleteBtn: Button
    lateinit var saveBtn: Button
    lateinit var diaryTextView: TextView
    lateinit var diaryContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ViewModel 객체 생성
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val intent = Intent(this, SelectedDateActivity::class.java)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            binding.diaryTextView.visibility = View.VISIBLE
            binding.goToWriteBtn.visibility = View.VISIBLE
            binding.diaryTextView.text = viewModel.selectedDate
            checkDay(year, month, dayOfMonth, viewModel.userID)
            // RecordWriteAcitivity : 넘기고자 하는 Component

        }
        binding.goToWriteBtn.setOnClickListener {
            intent.putExtra("selectedDate", viewModel.selectedDate)
            startActivity(intent)
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        viewModel.fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"


        var fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            val fileData = ByteArray(fileInputStream.available())
            fileInputStream.read(fileData)
            fileInputStream.close()
            str = String(fileData)
            binding.goToWriteBtn.visibility = View.INVISIBLE
            if (diaryContent.text == null) {
                diaryContent.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}