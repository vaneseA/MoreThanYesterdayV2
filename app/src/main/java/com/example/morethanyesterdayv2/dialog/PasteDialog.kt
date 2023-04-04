package com.example.morethanyesterdayv2.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.DialogPasteBinding
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.ui.activity.MainActivity

class PasteDialog(
//    pasteDialogInterface: MainActivity,
//    position: Int,
//    exerciseEntity: ExerciseEntity,
//    exerciseId: String,
//    selectedDate: String,
) : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DialogPasteBinding? = null
    private val binding get() = _binding!!
    private var pasteDialogInterface: PasteDialogInterface? = null
    var exerciseEntity: ExerciseEntity? = null
    var recordEntity: RecordEntity? = null
    private var position: Int? = null
    private var exerciseId: String? = null
    private var selectedDate: String? = null
    private var exerciseName: String? = null


    init {
        this.exerciseEntity = exerciseEntity
        this.recordEntity = recordEntity
        this.position = position
        this.pasteDialogInterface = pasteDialogInterface
        this.exerciseId = exerciseId
        this.selectedDate = selectedDate
        this.exerciseName = exerciseName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPasteBinding.inflate(inflater, container, false)
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


        return view
    }

    interface PasteDialogInterface {
        fun onYesButtonClick(id: Int)

    }
}