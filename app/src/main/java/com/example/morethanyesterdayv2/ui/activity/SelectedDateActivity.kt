package com.example.morethanyesterdayv2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.morethanyesterdayv2.AboutRoom.RecordListAdapter
import com.example.morethanyesterdayv2.AboutRoom.RoomHelper
import com.example.morethanyesterdayv2.AboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.AboutRoom.ExerciseDAO
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectedDateActivity : AppCompatActivity() {

    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper
    lateinit var memoAdapter: RecordListAdapter
    val memoList = mutableListOf<ExerciseEntity>()
    lateinit var memoDAO: ExerciseDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper =
            Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
//                .allowMainThreadQueries()//공부할때만 쓴다
                .build()
        memoDAO = helper.exerciseDAO()

        memoAdapter = RecordListAdapter(memoList)

        refreshAdapter()

        with(binding) {
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@SelectedDateActivity)

        }
        binding.SelectExerciseBtn.setOnClickListener {
            startActivity(Intent(this, SelectExerciseActivity::class.java))
        }
    }

    fun insertMemo(memo: ExerciseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            memoDAO.insert(memo)
            refreshAdapter()
        }
    }

    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            memoList.clear()
            memoList.addAll(memoDAO.getAll())
            withContext(Dispatchers.Main) {
                memoAdapter.notifyDataSetChanged()
            }
        }
    }
}