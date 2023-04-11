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
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.morethanyesterdayv2.R
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.databinding.CustomAddSetDialogBinding
import com.example.morethanyesterdayv2.repository.RecordRepository
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import com.example.morethanyesterdayv2.viewmodel.AddSetDialogViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

lateinit var appDatabase: AppDatabase
lateinit var recordDAO: RecordDAO
lateinit var repository: RecordRepository
lateinit var exerciseDAO: ExerciseDAO

class AddSetDialog(
    addSetDialogInterface: SelectedDateActivity,
    position: Int,
    exerciseEntity: ExerciseEntity,
    exerciseId: String,
    selectedDate: String
) : DialogFragment() {


    lateinit var viewModel: AddSetDialogViewModel

    // 뷰 바인딩 정의
    private var _binding: CustomAddSetDialogBinding? = null
    private val binding get() = _binding!!
    private var addSetDialogInterface: AddSetDialogInterface? = null
    var exerciseEntity: ExerciseEntity? = null
    var recordEntity: RecordEntity? = null
    private var position: Int? = null
    private var exerciseId: String? = null

    init {
        this.exerciseEntity = exerciseEntity
        this.recordEntity = recordEntity
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

        // AppDatabase 초기화
        appDatabase = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "room_db"
        ).build()
        recordDAO = appDatabase.recordDAO()

        repository = RecordRepository(recordDAO)
        exerciseDAO = appDatabase.exerciseDAO()

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel = ViewModelProvider(this).get(AddSetDialogViewModel::class.java)

        viewModel.currentKg.observe(this, Observer {
            binding.userInputKg?.setText(it.toString())
        })
        viewModel.currentLb.observe(this, Observer {
            binding.userInputLb?.setText(it.toString())
        })

        viewModel.currentCountValue.observe(this, Observer {
            binding.userInputCount?.setText(it.toString())
        })
        binding.userInputLb.visibility = View.GONE
        binding.showKg.visibility = View.GONE
        binding.userInputLb.visibility = View.GONE
        binding.plusFiveLbBtn.visibility = View.GONE
        binding.minusFiveLbBtn.visibility = View.GONE
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.radio_kg -> {
                        binding.kgOrLb.text = "kg"
                        binding.lbOrKg.text = "lb"
                        binding.showKg.visibility = View.GONE
                        binding.userInputKg.visibility = View.VISIBLE
                        binding.showLb.visibility = View.VISIBLE
                        binding.userInputLb.visibility = View.GONE
                        binding.plusFiveLbBtn.visibility = View.GONE
                        binding.minusFiveLbBtn.visibility = View.GONE
                        binding.plusFiveKgBtn.visibility = View.VISIBLE
                        binding.minusFiveKgBtn.visibility = View.VISIBLE
                    }
                    R.id.radio_lb -> {
                        binding.kgOrLb.text = "lb"
                        binding.lbOrKg.text = "kg"
                        binding.showKg.visibility = View.VISIBLE
                        binding.userInputKg.visibility = View.GONE
                        binding.showLb.visibility = View.GONE
                        binding.userInputLb.visibility = View.VISIBLE
                        binding.plusFiveLbBtn.visibility = View.VISIBLE
                        binding.minusFiveLbBtn.visibility = View.VISIBLE
                        binding.plusFiveKgBtn.visibility = View.GONE
                        binding.minusFiveKgBtn.visibility = View.GONE
                    }
                }
            }
        }

        //lb<->kg자동 변환
        var isKgSelected = true
        binding.userInputKg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // kgOrLb 와 lbOrKg 텍스트에 따라 값 변환
                val userInputKg = s?.toString()?.toFloatOrNull() ?: return // 유효하지 않은 값이면 무시
                if (isKgSelected) {
                    val convertToLb = userInputKg / 0.45359237f
                    binding.showLb.text = String.format("%.1f", convertToLb)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        var isLbSelected = true
        binding.userInputLb.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // kgOrLb 와 lbOrKg 텍스트에 따라 값 변환
                val userInputLb = s?.toString()?.toFloatOrNull() ?: return
                if (isLbSelected) {
                    val convertToLb = userInputLb * 0.45359237f
                    binding.showKg.text = String.format("%.1f", convertToLb)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.dialogExerciseName?.text = exerciseEntity?.exerciseName
        binding.dialogExerciseType?.text = exerciseEntity?.exerciseType

        binding.dialogCancelBtn?.setOnClickListener { dismiss() }
        binding.dialogAddBtn?.setOnClickListener {
            val selectedDateActivity = SelectedDateActivity.getInstance()
            appDatabase = Room.databaseBuilder(it.context, AppDatabase::class.java, "room_db")
                .build()

            // 사용자가 입력한 count 값을 문자열에서 정수로 변환하여 가져옴
            val count = binding.userInputCount?.text?.toString()?.toIntOrNull() ?: 0

            lifecycleScope.launch {
                // recordDAO을 이용해 ROOM 안에 있는 MaxKg 값을 가져옴
                val maxKg = withContext(Dispatchers.IO) {
                    repository.getMaxKgFromRecordByExerciseId(exerciseEntity?.exerciseId ?: "")
                }
                // recordDAO을 이용해 ROOM 안에 있는 totalSet 값을 가져옴
                val totalSet = withContext(Dispatchers.IO) {
                    repository.getCountSetFromRecordByExerciseId(exerciseEntity?.exerciseId ?: "")
                }
                // recordDAO을 이용해 ROOM 안에 있는 totalCount 값을 가져옴
                val totalCount = withContext(Dispatchers.IO) {
                    repository.getTotalCountFromRecordByExerciseId(exerciseEntity?.exerciseId ?: "")
                }
                // recordDAO을 이용해 ROOM 안에 있는 totalCount 값을 가져옴
                val totalKg = withContext(Dispatchers.IO) {
                    repository.getTotalKgFromExerciseByExerciseId(exerciseEntity?.exerciseId ?: "")
                }
                //라디오 버튼에 따라 저장하는 값을 달리하는 함수를 적용한 kg값
                val kg = getWeightValue().toDoubleOrNull() ?: 0.0

                // maxKg 업데이트
                if (maxKg != null) {
                    if (maxKg < kg) {
                        withContext(Dispatchers.IO) {
//                            repository.updateMaxKgByExerciseId(exerciseEntity?.exerciseId ?: "", kg)
                            exerciseDAO.updateMaxKgByExerciseId(
                                exerciseEntity?.exerciseId ?: "",
                                kg
                            )
                        }
                    }
                } else {
                    withContext(Dispatchers.IO) {
//                        repository.updateMaxKgByExerciseId(exerciseEntity?.exerciseId ?: "", kg)
                        exerciseDAO.updateMaxKgByExerciseId(
                            exerciseEntity?.exerciseId ?: "",
                            kg
                        )
                    }
                }
                var newTotalCount = totalCount + count
                var newTotalKg = totalKg + (kg * count)
                val record = RecordEntity(
                    exerciseId = exerciseEntity?.exerciseId ?: "",
                    selectedDate = exerciseEntity?.selectedDate ?: "",
                    exerciseName = exerciseEntity?.exerciseName ?: "",
                    exerciseType = exerciseEntity?.exerciseType ?: "",
                    kg = getWeightValue().toDoubleOrNull() ?: 0.0,
                    exerciseCount = count,
                    totalSet = totalSet + 1,
                    totalKg = newTotalKg,
                    totalCount = newTotalCount,
                    maxKg = kg?.toDouble() ?: 0.0
                )
                val exercise = ExerciseEntity(
                    exerciseId = exerciseEntity?.exerciseId ?: "",
                    selectedDate = exerciseEntity?.selectedDate ?: "",
                    exerciseName = exerciseEntity?.exerciseName ?: "",
                    exerciseType = exerciseEntity?.exerciseType ?: "",
                    totalSet = totalSet + 1,
                    totalKg = newTotalKg,
                    totalCount = newTotalCount,
                    maxKg = kg?.toDouble() ?: 0.0
                )

                insertAndUpdateRecord(record, exercise)

                withContext(Dispatchers.IO) {
                    exerciseDAO.updateTotalCountFromExerciseByExerciseId(
                        exerciseEntity?.exerciseId ?: "",
                        newTotalCount
                    )

                    exerciseDAO.updateTotalSetFromExerciseByExerciseId(
                        exerciseEntity?.exerciseId ?: "",
                        totalSet + 1
                    )
                    exerciseDAO.updateTotalKgFromExerciseByExerciseId(
                        exerciseEntity?.exerciseId ?: "",
                        totalKg + (kg * count),
                    )
                }
                dialog?.dismiss()

                val intent = Intent(context, SelectedDateActivity::class.java)
                val selectedDate = selectedDateActivity?.intent?.getStringExtra("selectedDate")

                Log.d("selectedDate", selectedDate.toString())
                intent.putExtra("selectedDate", selectedDate)
                context?.startActivity(intent)
                (context as SelectedDateActivity).finish()
            }
        }
        lifecycleScope.launch {
            // recordDAO을 이용해 ROOM 안에 있는 totalSet 값을 가져옴
            val totalSet = withContext(Dispatchers.IO) {
                repository.getRecordCountFromRecordByExerciseId(
                    exerciseEntity?.exerciseId ?: ""
                ) + 1
            }
            binding.dialogSet.text = "${totalSet}번째 세트"
        }
        binding.plusFiveKgBtn?.setOnClickListener {
            val currentKg = binding.userInputKg.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentKg + 5
            binding.userInputKg.setText(newValue.toString())
        }
        binding.minusFiveKgBtn?.setOnClickListener {
            val currentKg = binding.userInputKg.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentKg - 5
            binding.userInputKg.setText(newValue.toString())
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

        binding.plusFiveLbBtn?.setOnClickListener {
            val currentLb = binding.userInputLb.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentLb + 5
            binding.userInputLb.setText(newValue.toString())
        }
        binding.minusFiveLbBtn?.setOnClickListener {
            val currentLb = binding.userInputLb.text.toString().toDoubleOrNull() ?: 0.0
            val newValue = currentLb - 5
            binding.userInputLb.setText(newValue.toString())
        }


        return view
    }

    //라디오 버튼에 따라 저장하는 값을 달리하는 함수
    private fun getWeightValue(): String {
        // 사용자가 입력한 kg 값을 문자열에서 실수로 변환하여 가져옴
        val kgValue = binding.userInputKg?.text?.toString()?.toDoubleOrNull() ?: 0.0
        val lbToKg = binding.showKg?.text?.toString()?.toDoubleOrNull() ?: 0.0
        val lbValue = binding.userInputLb?.text?.toString()?.toDoubleOrNull() ?: 0.0
        return if (binding.radioKg.isChecked) {
            kgValue.toString()
        } else if (binding.radioLb.isChecked) {
            Toast.makeText(
                requireContext(),
                "${lbValue}lb는 \n${lbToKg}kg으로 치환하여 저장되었습니다.",
                Toast.LENGTH_LONG
            )
                .show()
            lbToKg.toString()
        } else {
            "0"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

fun insertAndUpdateRecord(record: RecordEntity, exercise: ExerciseEntity) {
    CoroutineScope(Dispatchers.IO).launch {
        repository.insert(record)
        exerciseDAO.update(exercise)
    }
}


interface AddSetDialogInterface {
    fun onYesButtonClick(id: Int)

}
