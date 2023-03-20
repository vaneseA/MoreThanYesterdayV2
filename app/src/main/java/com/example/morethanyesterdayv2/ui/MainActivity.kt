package com.example.morethanyesterdayv2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.morethanyesterdayv2.databinding.ActivityMainBinding
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private var selectedDate = ""


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

//        val intent = Intent(this, SelectedDateRecordActivity::class.java)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            binding.diaryTextView.visibility = View.VISIBLE
            binding.goToWriteBtn.visibility = View.VISIBLE
            binding.diaryTextView.text = selectedDate
            checkDay(year, month, dayOfMonth, userID)
            // RecordWriteAcitivity : 넘기고자 하는 Component
            intent.putExtra("Date", selectedDate)
//            getExerciseListData(selectedDate)
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        //저장할 파일 이름설정
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

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
