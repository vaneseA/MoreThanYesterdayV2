package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.morethanyesterdayv2.aa.SelectedDateViewModel
import com.example.morethanyesterdayv2.aboutRoom.ExerciseDAO
import com.example.morethanyesterdayv2.aboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.aboutRoom.ParentAdapter
import com.example.morethanyesterdayv2.aboutRoom.RoomHelper
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.CustomDialog


class SelectedDateActivity : AppCompatActivity(), AddSetDialogInterface {


    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var recordListAdapter: ParentAdapter
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

        viewModel = ViewModelProvider(this).get(SelectedDateViewModel::class.java)

        viewModel.getExerciseList().observe(this, { list ->
            exerciseList.clear()
            exerciseList.addAll(list)
            recordListAdapter.notifyDataSetChanged()
        })

        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
                .build()
        exerciseDAO = helper.exerciseDAO()

        recordListAdapter = ParentAdapter(exerciseList, this@SelectedDateActivity)

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
        viewModel.loadExerciseList()
    }

    fun clickViewEvents(position: Int, member: ExerciseEntity) {
        val dialog = CustomDialog(this,position,member)
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "CustomDialog")
    }

    override fun onYesButtonClick(id: Int) {

    }

}