package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.morethanyesterdayv2.aboutRoom.ExerciseDAO
import com.example.morethanyesterdayv2.aboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.aboutRoom.RecordListAdapter
import com.example.morethanyesterdayv2.aboutRoom.RoomHelper
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectedDateActivity : AppCompatActivity() {

    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var memoAdapter: RecordListAdapter
    val exerciseList = mutableListOf<ExerciseEntity>()
    lateinit var exerciseDAO: ExerciseDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
//                .allowMainThreadQueries()//공부할때만 쓴다
                .build()
        exerciseDAO = helper.exerciseDAO()

        memoAdapter = RecordListAdapter(exerciseList,this@SelectedDateActivity)

        refreshAdapter()

        with(binding) {
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@SelectedDateActivity)

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
                memoAdapter.notifyDataSetChanged()
            }
        }
    }


}