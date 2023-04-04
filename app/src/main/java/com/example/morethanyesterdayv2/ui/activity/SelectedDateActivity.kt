package com.example.morethanyesterdayv2.ui.activity

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
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.ActivitySelectedDateBinding
import com.example.morethanyesterdayv2.dialog.AddSetDialogInterface
import com.example.morethanyesterdayv2.dialog.AddSetDialog
import com.example.morethanyesterdayv2.dialog.DeleteDialog
import com.example.morethanyesterdayv2.dialog.DeleteDialogInterface


class SelectedDateActivity : AppCompatActivity(), AddSetDialogInterface, DeleteDialogInterface {

    //  by lazy는 프로퍼티 초기화를 지연시켜서 액티비티나 프래그먼트의 뷰가 생성될 때 불필요한 리소스 초기화를 피하고 애플리케이션 성능을 향상시킬 수 있다
    val binding by lazy { ActivitySelectedDateBinding.inflate(layoutInflater) }
    lateinit var appDatabase: AppDatabase
    lateinit var parentAdapter: ParentAdapter
    val exerciseList = mutableListOf<ExerciseEntity>()
    val recordList = mutableListOf<RecordEntity>()
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

        val exerciseName = intent?.getStringExtra("exerciseName") ?: ""
        Log.d("dddddd", exerciseName)
        val selectedDate = intent?.getStringExtra("selectedDate") ?: ""
        Log.d("dddddd", selectedDate)
        supportActionBar?.title = selectedDate



        viewModel = ViewModelProvider(this).get(SelectedDateViewModel::class.java)

        // 선택한 날짜에 해당하는 모든 운동 목록 가져오기
        viewModel.loadExerciseListByDate(selectedDate)



        viewModel.getExerciseList().observe(this, { list ->
            // 선택한 날짜에 해당하는 모든 운동 목록만 가져온다
            exerciseList.clear()
            exerciseList.addAll(list.filter { it.selectedDate == selectedDate })
            parentAdapter.notifyDataSetChanged()
        })


        appDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, "room_db")
                .build()
        exerciseDAO = appDatabase.exerciseDAO()

        parentAdapter =
            ParentAdapter(exerciseList, this@SelectedDateActivity, application, recordList)

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
        val exseciseName = intent?.getStringExtra("selectedDate") ?: ""
        Log.d("dddddd", exseciseName)
        viewModel.loadExerciseListByDate(selectedDate)
    }

    fun showAddSetDialog(position: Int, exerciseEntity: ExerciseEntity, exerciseId: String, selectedDate: String) {
        val selectedDate = intent.getStringExtra("selectedDate")
        val dialog = selectedDate?.let {
            AddSetDialog(this, position, exerciseEntity, exerciseId,
                it
            )
        }
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog?.isCancelable = false
        dialog?.show(supportFragmentManager, "CustomDialog")
    }

    fun showDeleteDialog(position: Int, exerciseEntity: ExerciseEntity, exerciseId: String, selectedDate: String) {
        val selectedDate = intent.getStringExtra("selectedDate")
        val dialog = selectedDate?.let {
            DeleteDialog(this, position, exerciseEntity, exerciseId,
                it
            )
        }
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog?.isCancelable = false
        dialog?.show(supportFragmentManager, "CustomDialog")
    }

    override fun onYesButtonClick(id: Int) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

}