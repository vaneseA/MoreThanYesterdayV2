package com.example.morethanyesterdayv2.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.databinding.CustomAddSetDialogBinding
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appDatabase: AppDatabase
lateinit var recordDAO: RecordDAO

class CustomDialog(
    addSetDialogInterface: SelectedDateActivity,
    position: Int,
    exerciseEntity: ExerciseEntity,
    exerciseId: String,
    selectedDate: String
) : DialogFragment() {


    lateinit var customDialogViewModel: CustomDialogViewModel

    // 뷰 바인딩 정의
    private var _binding: CustomAddSetDialogBinding? = null
    private val binding get() = _binding!!
    private var addSetDialogInterface: AddSetDialogInterface? = null
    var exerciseEntity: ExerciseEntity? = null
    private var position: Int? = null
    private var exerciseId: String? = null

    init {
        this.exerciseEntity = exerciseEntity
        this.position = position
        this.addSetDialogInterface = addSetDialogInterface
        this.exerciseId = exerciseId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CustomAddSetDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        customDialogViewModel = ViewModelProvider(this).get(CustomDialogViewModel::class.java)

        customDialogViewModel.currentWeightValue.observe(this, Observer {
            binding.userInputWeight?.setText(it.toString())
        })
        customDialogViewModel.currentCountValue.observe(this, Observer {
            binding.userInputCount?.setText(it.toString())
        })
        var isKgSelected = true
        binding.userInputWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // kgOrLb 와 lbOrKg 텍스트에 따라 값 변환
                val userInputValue = s?.toString()?.toFloatOrNull() ?: return // 유효하지 않은 값이면 무시

                if (isKgSelected) {
                    val convertToLb = userInputValue / 0.45359237f
                    binding.showOtherWeight.text = String.format("%.1f", convertToLb)
                } else {
                    val convertToKg = userInputValue * 0.45359237f
                    binding.showOtherWeight.text = String.format("%.1f", convertToKg)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


//        binding.radioLb.setOnClickListener {
//            // kgOrLb 와 lbOrKg 텍스트 변경
//            binding.kgOrLb.text = "lg"
//            binding.lbOrKg.text = "kg"
//
//            // userInputWeight 값 업데이트
//            val userInputValue = binding.userInputWeight.text.toString().toFloat()
//            val convertToLb = userInputValue / 2.2046f
//
//            binding.showOtherWeight.text = String.format("%.1f", convertToLb)
//            binding.userInputWeight.setText(binding.showOtherWeight.text)
//            Toast.makeText(requireContext(), "변환시, 소수점으로 인해 \n약간의 무게 차이가 있으니 참고바랍니다.", Toast.LENGTH_LONG)
//                .show()
//        }
//        binding.radioKg.setOnClickListener {
//            // kgOrLb 와 lbOrKg 텍스트 변경
//            binding.kgOrLb.text = "kg"
//            binding.lbOrKg.text = "lg"
//
//            // userInputWeight 값 업데이트
//            val userInputValue = binding.userInputWeight.text.toString().toFloat()
//            val convertToKg = userInputValue * 2.2046f
//            binding.showOtherWeight.text = String.format("%.1f", convertToKg)
//            binding.userInputWeight.setText(binding.showOtherWeight.text)
//            Toast.makeText(requireContext(), "변환시, 소수점으로 인해 \n약간의 무게 차이가 있으니 참고바랍니다.", Toast.LENGTH_LONG)
//                .show()
//        }
        binding.dialogExerciseName?.text = exerciseEntity?.exerciseName
        binding.dialogExerciseType?.text = exerciseEntity?.exerciseType

        binding.dialogCancleBtn?.setOnClickListener { dismiss() }
        binding.dialogAddBtn?.setOnClickListener() {
            val selectedDateActivity = SelectedDateActivity.getInstance()
            appDatabase =
                Room.databaseBuilder(it.context, AppDatabase::class.java, "room_db")
                    .build()
            recordDAO = appDatabase.recordDAO()
            // 사용자가 입력한 kg 값을 문자열에서 실수로 변환하여 가져옴
            val kg = binding.userInputWeight?.text?.toString()?.toDoubleOrNull() ?: 0.0

            // 사용자가 입력한 count 값을 문자열에서 정수로 변환하여 가져옴
            val count = binding.userInputCount?.text?.toString()?.toIntOrNull() ?: 0

            // record 객체의 kg와 count 속성에 값을 대입
            val record = RecordEntity(
                exerciseId = exerciseEntity?.exerciseId ?: "",
                selectedDate = exerciseEntity?.selectedDate ?: "",
                exerciseName = exerciseEntity?.exerciseName ?: "",
                exerciseType = exerciseEntity?.exerciseType ?: "",
                kg = kg.toString(),
                count = count.toString()
            )
            insertRecord(record)
            dialog?.dismiss()

            val intent = Intent(context, SelectedDateActivity::class.java)
            val selectedDate = selectedDateActivity?.intent?.getStringExtra("selectedDate")

            Log.d("selectedDate", selectedDate.toString())
            intent.putExtra("selectedDate", selectedDate)
            context?.startActivity(intent)
            (context as SelectedDateActivity).finish()
        }
        var setCount = ""
        binding.dialogSet.text = setCount
        binding.plusFiveBtn?.setOnClickListener {
            val currentWeight = binding.userInputWeight.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentWeight + 5
            binding.userInputWeight.setText(newValue.toString())
        }
        binding.minusFiveBtn?.setOnClickListener {
            val currentWeight = binding.userInputWeight.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentWeight - 5
            binding.userInputWeight.setText(newValue.toString())
        }
        binding.plusOneBtn?.setOnClickListener {
            val currentCount = binding.userInputCount.text.toString().toIntOrNull() ?: 0
            val newValue = currentCount + 1
            binding.userInputCount.setText(newValue.toString())
        }
        binding.minusOneBtn?.setOnClickListener {
            val currentCount = binding.userInputCount.text.toString().toIntOrNull() ?: 0
            val newValue = currentCount - 1
            binding.userInputCount.setText(newValue.toString())
        }

        return view
    }

    private fun convertWeight() {
        val weightInKg = binding.userInputWeight.text.toString().toDoubleOrNull() ?: return

        val weightInLb = weightInKg * 2.20462
        val weightInKgFormatted = "%.0f".format(weightInKg)
        val weightInLbFormatted = "%.0f".format(weightInLb)

        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_kg -> {
                binding.kgOrLb.text = "kg"
                binding.showOtherWeight.text = weightInLbFormatted + " lb"
            }
            R.id.radio_lb -> {
                binding.kgOrLb.text = "lb"
                binding.showOtherWeight.text = weightInKgFormatted + " kg"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

fun insertRecord(record: RecordEntity) {
    CoroutineScope(Dispatchers.IO).launch {
        recordDAO.insert(record)
    }
}


interface AddSetDialogInterface {
    fun onYesButtonClick(id: Int)

}
