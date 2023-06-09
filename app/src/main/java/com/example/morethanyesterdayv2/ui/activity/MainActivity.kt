package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.databinding.ActivityMainBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialog
import com.example.morethanyesterdayv2.dialog.PasteDialog
import com.example.morethanyesterdayv2.ui.adapter.MainAdapter
import com.example.morethanyesterdayv2.ui.adapter.ParentAdapter
import com.example.morethanyesterdayv2.viewmodel.MainViewModel

import java.io.FileInputStream
import java.util.*

class MainActivity : AppCompatActivity(), PasteDialog.PasteDialogInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel // ViewModel 객체 선언


    lateinit var mainAdapter: MainAdapter
    val exerciseList = mutableListOf<ExerciseEntity>()


    lateinit var fname: String
    lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ViewModel 객체 생성
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val intent = Intent(this, SelectedDateActivity::class.java)

        // 오늘 날짜 자동 선택
        val today = Calendar.getInstance()
        handleCalendarSelection(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )


        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            binding.goToWriteBtn.visibility = View.VISIBLE
            binding.selectedDateTextView.text = viewModel.selectedDate
            checkDay(year, month, dayOfMonth)


            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            // 선택한 날짜에 해당하는 운동 목록 가져오기
            viewModel.loadExerciseListByDate(viewModel.selectedDate)


            viewModel.getExerciseList().observe(this, { list ->
                // 선택한 날짜에 해당하는 운동 목록만 가져온다
                exerciseList.clear()
                exerciseList.addAll(list.filter { it.selectedDate == viewModel.selectedDate })
                mainAdapter.notifyDataSetChanged()
            })
        }

        binding.pasteBtn.setOnClickListener {
            showPasteDialog(viewModel.selectedDate)
            Log.d("selectedDateMain", viewModel.selectedDate)
        }
        binding.goToWriteBtn.setOnClickListener {
            intent.putExtra("selectedDate", viewModel.selectedDate)
            startActivity(intent)
        }
        mainAdapter = MainAdapter(exerciseList, this@MainActivity)

        binding.mainRecordRV.adapter = mainAdapter
        binding.mainRecordRV.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getExerciseList().observe(this, { list ->
            exerciseList.clear()
            exerciseList.addAll(list.filter { it.selectedDate == viewModel.selectedDate })
            mainAdapter.notifyDataSetChanged()

            // exerciseList가 비어 있으면 pasteBtn을 숨기고 goToWriteBtn을 나타나게 한다
            if (exerciseList.isEmpty()) {
                binding.pasteBtn.visibility = View.GONE
                binding.goToWriteBtn.visibility = View.VISIBLE
            } else {
                binding.pasteBtn.visibility = View.VISIBLE
                binding.goToWriteBtn.visibility = View.GONE
            }
        })
    }


    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int) {
        viewModel.fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"


        var fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            val fileData = ByteArray(fileInputStream.available())
            fileInputStream.read(fileData)
            fileInputStream.close()
            str = String(fileData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showPasteDialog(
        selectedDate: String
    ) {

        val dialog = PasteDialog(this, selectedDate)
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "PasteDialog")
    }


    fun handleCalendarSelection(year: Int, month: Int, dayOfMonth: Int) {
        viewModel.selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
        binding.selectedDateTextView.text = viewModel.selectedDate
        checkDay(year, month, dayOfMonth)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // 선택한 날짜에 해당하는 운동 목록 가져오기
        viewModel.loadExerciseListByDate(viewModel.selectedDate)

        viewModel.getExerciseList().observe(this, { list ->
            // 선택한 날짜에 해당하는 운동 목록만 가져온다
            exerciseList.clear()
            exerciseList.addAll(list.filter { it.selectedDate == viewModel.selectedDate })
            mainAdapter.notifyDataSetChanged()
        })


    }

    override fun onYesButtonClick(id: Int) {}
}