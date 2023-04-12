package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentAbsBinding
import com.example.morethanyesterdayv2.databinding.FragmentChestBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter
import com.example.morethanyesterdayv2.viewmodel.SelectExerciseViewModel

class ChestFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentChestBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): ChestFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return ChestFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedDate = arguments?.getString(ARG_SELECTED_DATE) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChestBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "벤치 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "덤벨 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "인클라인 벤치 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "디클라인 벤치 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "딥스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "푸쉬 업", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "체스트 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "펙 덱 플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "시티드 체스트 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "인클라인 덤벨 프레스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "인클라인 딥스", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "케이블 크로스오버", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "원암 덤벨 푸쉬", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "원암 덤벨 플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "덤벨 플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "펙 덱 플라이", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "펙 덱 머신", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "펙 덱 푸쉬업", type = "가슴"))
        exerciseDataList.add(ExerciseData(name = "펙 덱 패드 체스트", type = "가슴"))

        return binding.root
    }
}