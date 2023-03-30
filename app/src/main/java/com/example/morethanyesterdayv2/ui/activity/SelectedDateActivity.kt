package com.example.morethanyesterdayv2.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.morethanyesterdayv2.viewmodel.SelectedDateViewModel
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.ui.adapter.ParentAdapter
import com.example.morethanyesterdayv2.aboutRoom.RoomHelper
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.CustomDialog


class SelectedDateActivity : AppCompatActivity(), AddSetDialogInterface {


    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var parentAdapter: ParentAdapter
    val exerciseList = mutableListOf<ExerciseEntity>()
    lateinit var exerciseDAO: ExerciseDAO

    init {
        instance = this
    }

    companion object {
        private var instance: SelectedDateActivity? = null
        fun getInstance(): SelectedDateActivity? {
            return instance
        }
    }
    private lateinit var viewModel: SelectedDateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        supportActionBar?.title = selectedDate


        viewModel = ViewModelProvider(this).get(SelectedDateViewModel::class.java)
        // 선택한 날짜에 해당하는 운동 목록 가져오기
        viewModel.loadExerciseListByDate(selectedDate)

        viewModel.getExerciseList().observe(this, { list ->
            // 선택한 날짜에 해당하는 운동 목록만 가져온다
            exerciseList.clear()
            exerciseList.addAll(list.filter { it.selectedDate == selectedDate })
            parentAdapter.notifyDataSetChanged()
        })

        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
                .build()
        exerciseDAO = helper.exerciseDAO()

        parentAdapter = ParentAdapter(exerciseList, this@SelectedDateActivity)

        with(binding) {
            recordRV.adapter = parentAdapter
            recordRV.layoutManager = LinearLayoutManager(this@SelectedDateActivity)

        }
        binding.SelectExerciseBtn.setOnClickListener {
            val intent = Intent(this, SelectExerciseActivity::class.java)
            intent.putExtra("selectedDate", selectedDate)
            startActivity(intent)
            finish()
        }
    }

    fun refreshAdapter() {
        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        viewModel.loadExerciseListByDate(selectedDate)
    }
    fun clickViewEvents(position: Int, member: ExerciseEntity) {
        val dialog = CustomDialog(this,position,member)
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "CustomDialog")
    }

    override fun onYesButtonClick(id: Int) {

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        val mainActivity = MainActivity.getInstance()
        val intent = Intent().apply {
            putExtra("selectedDate", selectedDate)
            Log.d("selectedDatess",selectedDate)
        }
        setResult(Activity.RESULT_OK, intent)
//        finish()
        val year = selectedDate.substringBefore("년").toInt()
        val month = selectedDate.substringAfter("년 ").substringBefore("월").toInt() - 1
        val dayOfMonth = selectedDate.substringAfter("월 ").substringBefore("일").toInt()
        startActivity(Intent(this,MainActivity::class.java))
        mainActivity?.checkDay(2023,6,12)

    }

}