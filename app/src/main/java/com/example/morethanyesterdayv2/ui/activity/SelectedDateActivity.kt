package com.example.morethanyesterdayv2.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.aboutRecord.ActionType
import com.example.morethanyesterdayv2.aboutRecord.MynumberViewModel
import com.example.morethanyesterdayv2.aboutRoom.ExerciseDAO
import com.example.morethanyesterdayv2.aboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.aboutRoom.RecordListAdapter
import com.example.morethanyesterdayv2.aboutRoom.RoomHelper
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import com.example.morethanyesterdayv2.dialog.ConfirmDialogInterface
import com.example.morethanyesterdayv2.dialog.CustomDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectedDateActivity : AppCompatActivity(), ConfirmDialogInterface {

//    private lateinit var mynumberViewModel: MynumberViewModel

    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var recordListAdapter: RecordListAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
//                .allowMainThreadQueries()//공부할때만 쓴다
                .build()
        exerciseDAO = helper.exerciseDAO()

        recordListAdapter = RecordListAdapter(exerciseList, this@SelectedDateActivity)

        refreshAdapter()

        with(binding) {
            recordRV.adapter = recordListAdapter
            recordRV.layoutManager = LinearLayoutManager(this@SelectedDateActivity)

        }
        binding.SelectExerciseBtn.setOnClickListener {
            startActivity(Intent(this, SelectExerciseActivity::class.java))
        }


    }

    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseList.clear()
            exerciseList.addAll(exerciseDAO.getAll())
            withContext(Dispatchers.Main) {
                recordListAdapter.notifyDataSetChanged()
            }
        }
    }

    // 뷰 클릭 이벤트 정의
    fun clickViewEvents(postion: Int, member: ExerciseEntity) {
            val dialog = CustomDialog(this, postion, member)
            // 알림창이 띄워져있는 동안 배경 클릭 막기
            dialog.isCancelable = false
            dialog.show(this.supportFragmentManager, "ConfirmDialog")
    }

    override fun onYesButtonClick(id: Int) {
        TODO("Not yet implemented")
    }

}