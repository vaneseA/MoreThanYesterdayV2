package com.example.morethanyesterdayv2.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.morethanyesterdayv2.aboutRecord.ActionType
import com.example.morethanyesterdayv2.aboutRecord.MynumberViewModel
import com.example.morethanyesterdayv2.aboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.databinding.CustomAddSetDialogBinding

class CustomDialog(
    confirmDialogInterface: ConfirmDialogInterface,
    position: Int, member: ExerciseEntity
) : DialogFragment() {

    lateinit var mynumberViewModel: MynumberViewModel

    // 뷰 바인딩 정의
    private var _binding: CustomAddSetDialogBinding? = null
    private val binding get() = _binding!!

    private var confirmDialogInterface: ConfirmDialogInterface? = null

    private var member: String? = null
    private var position: Int? = null

    init {
        this.member = member.toString()
        this.position = position
        this.confirmDialogInterface = confirmDialogInterface
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

        mynumberViewModel = ViewModelProvider(this).get(MynumberViewModel::class.java)

        mynumberViewModel.currentWeightValue.observe(this, Observer {
            binding.userInputWeight?.text = it.toString()
        })
        mynumberViewModel.currentCountValue.observe(this, Observer {
            binding.userInputCount?.text = it.toString()
        })

//        binding.dialogExerciseName?.text = member.exerciseName
//        binding.dialogExerciseType?.text = member.exerciseType

        binding.dialogCancleBtn?.setOnClickListener { dismiss() }
        binding.dialogAddBtn?.setOnClickListener { dismiss() }

        binding.plusFiveBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.WEIGHTPLUS,
                5
            )
        }
        binding.minusFiveBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.WEIGHTMINUS,
                5
            )
        }
        binding.plusOneBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.COUNTPLUS,
                1
            )
        }
        binding.minusOneBtn?.setOnClickListener {
            mynumberViewModel.updateValue(
                actionType = ActionType.COUNTMINUS,
                1
            )
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface ConfirmDialogInterface {
    fun onYesButtonClick(id: Int)
}