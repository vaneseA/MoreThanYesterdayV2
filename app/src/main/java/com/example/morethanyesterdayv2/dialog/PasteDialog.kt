package com.example.morethanyesterdayv2.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.databinding.DialogPasteBinding
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.ui.activity.MainActivity
import com.example.morethanyesterdayv2.viewmodel.DialogPasteViewModel
import java.io.FileInputStream
import java.util.*

class PasteDialog(
    pasteDialogInterface: MainActivity,
//    selectedDate: String,
) : DialogFragment() {

    lateinit var viewModel: DialogPasteViewModel

    // 뷰 바인딩 정의
    private var _binding: DialogPasteBinding? = null
    private val binding get() = _binding!!
    private var pasteDialogInterface: PasteDialogInterface? = null
    private var selectedDate: String? = null
    private var appDatabase: AppDatabase? = null
    private var recordDAO: RecordDAO? = null
    private var exerciseDAO: ExerciseDAO? = null

    init {
        this.selectedDate = selectedDate
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
            viewModel.selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
            checkDay(year, month, dayOfMonth, view.context)

        }

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


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
        viewModel.selectedDate = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
        checkDay(year, month, dayOfMonth, context)

        viewModel = ViewModelProvider(this).get(DialogPasteViewModel::class.java)
    }

    interface PasteDialogInterface {
        fun onYesButtonClick(id: Int)

    }
}