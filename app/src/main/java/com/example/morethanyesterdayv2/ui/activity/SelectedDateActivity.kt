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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectedDateActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mynumberViewModel: MynumberViewModel

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

        mynumberViewModel = ViewModelProvider(this).get(MynumberViewModel::class.java)
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

    fun addSetDialog(position: Int, member: ExerciseEntity) {
        val dialogView =
            LayoutInflater.from(this@SelectedDateActivity)
                .inflate(R.layout.custom_add_set_dialog, null)
        val builder = AlertDialog.Builder(this@SelectedDateActivity)
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = builder.show()

        //dialog radius줘서 모서리 둥글게
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //binding 전까지는 이거
        val dialogExerciseName = alertDialog.findViewById<TextView>(R.id.dialogExerciseName)
        val dialogExerciseType = alertDialog.findViewById<TextView>(R.id.dialogExerciseType)
        val dialogCancleBtn = alertDialog.findViewById<Button>(R.id.dialogCancleBtn)
        val dialogAddBtn = alertDialog.findViewById<Button>(R.id.dialogAddBtn)

        val plusFiveBtn = alertDialog.findViewById<TextView>(R.id.plusFiveBtn)
        val minusFiveBtn = alertDialog.findViewById<TextView>(R.id.minusFiveBtn)
        val plusOneBtn = alertDialog.findViewById<TextView>(R.id.plusOneBtn)
        val minusOneBtn = alertDialog.findViewById<TextView>(R.id.minusOneBtn)
        val userInputWeight = alertDialog.findViewById<TextView>(R.id.userInputWeight)
        val userInputCount = alertDialog.findViewById<TextView>(R.id.userInputCount)

        mynumberViewModel.currentWeightValue.observe(this, Observer {
            userInputWeight?.text = it.toString()
        })
        mynumberViewModel.currentCountValue.observe(this, Observer {
            userInputCount?.text = it.toString()
        })

        dialogExerciseName?.text = member.exerciseName
        dialogExerciseType?.text = member.exerciseType

        dialogCancleBtn?.setOnClickListener { alertDialog.dismiss() }
        dialogAddBtn?.setOnClickListener { alertDialog.dismiss() }

        plusFiveBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.WEIGHTPLUS,
                5
            )
        }
        minusFiveBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.WEIGHTMINUS,
                5
            )
        }
        plusOneBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.COUNTPLUS,
                1
            )
        }
        minusOneBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.COUNTMINUS,
                1
            )
        }


    }

    override fun onClick(view: View?) {
        //뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when (view) {
        }
    }

}