package com.example.morethanyesterdayv2.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.DeleteDialogBinding
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteDialog(
    deleteDialogInterface: SelectedDateActivity,
    position: Int,
    exerciseEntity: ExerciseEntity,
    exerciseId: String,
    selectedDate: String
) : DialogFragment() {

    lateinit var viewModel: DeleteDialogViewModel

    // 뷰 바인딩 정의
    private var _binding: DeleteDialogBinding? = null
    private val binding get() = _binding!!
    private var deleteDialogInterface: DeleteDialogInterface? = null
    var exerciseEntity: ExerciseEntity? = null
    var recordEntity: RecordEntity? = null
    private var position: Int? = null
    private var exerciseId: String? = null

    init {
        this.exerciseEntity = exerciseEntity
        this.recordEntity = recordEntity
        this.position = position
        this.deleteDialogInterface = deleteDialogInterface
        this.exerciseId = exerciseId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeleteDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // AppDatabase와 RecordDAO 초기화
        appDatabase = Room.databaseBuilder(
            view.context,
            AppDatabase::class.java,
            "room_db"
        ).build()
        recordDAO = appDatabase.recordDAO()
        exerciseDAO = appDatabase.exerciseDAO()
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.noBtnForDelete.setOnClickListener { dialog?.dismiss() }
        binding.yesBtnForDelete.setOnClickListener {
            val selectedDateActivity = SelectedDateActivity.getInstance()
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    recordDAO.deleteByExerciseId(
                        exerciseEntity?.exerciseId ?: ""
                    )
                    exerciseDAO.deleteByExerciseId(
                        exerciseEntity?.exerciseId ?: ""
                    )
                }
            }
            dialog?.dismiss()

            val intent = Intent(context, SelectedDateActivity::class.java)
            val selectedDate = selectedDateActivity?.intent?.getStringExtra("selectedDate")

            Log.d("selectedDate", selectedDate.toString())
            intent.putExtra("selectedDate", selectedDate)
            context?.startActivity(intent)
            (context as SelectedDateActivity).finish()
        }
        return view
    }
}

interface DeleteDialogInterface {
    fun onYesButtonClick(id: Int)

}