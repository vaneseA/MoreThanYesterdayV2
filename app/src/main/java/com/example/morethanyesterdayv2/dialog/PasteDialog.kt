package com.example.morethanyesterdayv2.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.databinding.DialogPasteBinding
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.ui.activity.MainActivity
import com.example.morethanyesterdayv2.viewmodel.DialogPasteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.util.*

class PasteDialog(
    pasteDialogInterface: MainActivity,
    selectedDate: String,
) : DialogFragment() {

    lateinit var viewModel: DialogPasteViewModel

    // 뷰 바인딩 정의
    private var _binding: DialogPasteBinding? = null
    private val binding get() = _binding!!
    private var pasteDialogInterface: PasteDialogInterface? = null
    var exerciseEntity: ExerciseEntity? = null
    var recordEntity: RecordEntity? = null
    private var selectedDate: String? = null
    private var newSelectedDate: String? = null
    private var appDatabase: AppDatabase? = null
    private var recordDAO: RecordDAO? = null
    private var exerciseDAO: ExerciseDAO? = null

    init {
        this.exerciseEntity = exerciseEntity
        this.recordEntity = recordEntity
        this.selectedDate = selectedDate
        this.newSelectedDate = newSelectedDate

        this.pasteDialogInterface = pasteDialogInterface
    }

    lateinit var str: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPasteBinding.inflate(inflater, container, false)
        val view = binding.root
        // ViewModel 객체 생성
        viewModel = ViewModelProvider(this).get(DialogPasteViewModel::class.java)

        // AppDatabase와 RecordDAO 초기화
        appDatabase = Room.databaseBuilder(
            view.context,
            AppDatabase::class.java,
            "room_db"
        ).build()
        recordDAO = appDatabase!!.recordDAO()
        exerciseDAO = appDatabase!!.exerciseDAO()

        // 오늘 날짜 자동 선택
        val today = Calendar.getInstance()
        handleCalendarSelection(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        binding.calendarViewForDialog.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.newSelectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            checkDay(year, month, dayOfMonth, view.context)

        }


        binding.pasteCancelBtn.setOnClickListener { dismiss() }
        binding.pasteAddBtn.setOnClickListener {

            appDatabase = Room.databaseBuilder(it.context, AppDatabase::class.java, "room_db")
                .build()

            lifecycleScope.launch {
                // 선택된 날짜의 운동 ID 목록을 가져옴
                val exerciseIds = withContext(Dispatchers.IO) {
                    exerciseDAO!!.getExerciseIdsBySelectedDate(selectedDate!!)

                }
                for (exerciseId in exerciseIds) {
                    val newExerciseId = UUID.randomUUID().toString()
                    // 운동 ID에 해당하는 운동 세트 목록을 가져옴
                    val exerciseEntities = withContext(Dispatchers.IO) {
                        exerciseDAO?.loadExerciseListLiveDataFromExerciseByExerciseId(exerciseId)
                    } ?: continue
                    for (exerciseEntity in exerciseEntities) {
                        // 운동 세트를 새로운 날짜와 운동 ID로 저장
                        val newExerciseEntity = ExerciseEntity(
                            exerciseName = exerciseEntity.exerciseName,
                            exerciseType = exerciseEntity.exerciseType,
                            totalSet = exerciseEntity.totalSet,
                            totalKg = exerciseEntity.totalKg,
                            maxKg = exerciseEntity.maxKg,
                            totalCount = exerciseEntity.totalCount,
                            selectedDate = viewModel.newSelectedDate!!, // 변경할 날짜
                            exerciseId = newExerciseId // 변경할 exerciseId
                        )
                        val recordEntities = withContext(Dispatchers.IO) {
                            recordDAO?.loadExerciseListLiveDataFromRecordByExerciseId(exerciseId)
                        } ?: continue
                        for (recordEntity in recordEntities) {
                            val record = RecordEntity(
                                exerciseId = newExerciseId,
                                selectedDate = viewModel.newSelectedDate!!, // 변경할 날짜
                                exerciseName = exerciseEntity?.exerciseName ?: "",
                                exerciseType = exerciseEntity?.exerciseType ?: "",
                                kg = recordEntity?.kg ?: 0.0,
                                exerciseCount = recordEntity?.exerciseCount ?: 0,
                                totalSet = recordEntity?.totalSet ?: 0,
                                totalKg = recordEntity?.totalKg ?: 0.0,
                                totalCount = recordEntity?.totalCount ?: 0,
                                maxKg = recordEntity?.maxKg ?: 0.0,
                            )
                            withContext(Dispatchers.IO) {
                                recordRepository.insert(record)
                            }
                        }
                        withContext(Dispatchers.IO) {
                            exerciseDAO!!.insert(newExerciseEntity)

                        }
                    }
                }
                // 저장 완료 메시지를 띄우고 다이얼로그를 닫음
                Toast.makeText(
                    requireContext(),
                    "저장 완료",
                    Toast.LENGTH_LONG
                ).show()

                dismiss()
            }

        }

        return view
    }


    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, context: Context?) {
        viewModel.fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

        context?.let { ctx ->
            var fileInputStream: FileInputStream
            try {
                fileInputStream = ctx.openFileInput(viewModel.fname)!!
                val fileData = ByteArray(fileInputStream.available())
                fileInputStream.read(fileData)
                fileInputStream.close()
                str = String(fileData)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleCalendarSelection(year: Int, month: Int, dayOfMonth: Int) {
        viewModel.newSelectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
        checkDay(year, month, dayOfMonth, context)

        viewModel = ViewModelProvider(this).get(DialogPasteViewModel::class.java)
    }

    interface PasteDialogInterface {
        fun onYesButtonClick(id: Int)

    }

}