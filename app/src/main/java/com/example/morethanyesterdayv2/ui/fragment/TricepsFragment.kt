package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentChestBinding
import com.example.morethanyesterdayv2.databinding.FragmentTricepsBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class TricepsFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentTricepsBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): TricepsFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return TricepsFragment().apply {
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
        binding = FragmentTricepsBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "스컬크러셔", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "로프 푸시다운", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "다이아몬드 푸시업", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "케이블 푸시다운", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "바벨 스컬크러셔", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "덤벨 킥백", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "케이블 삼두 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "바벨 삼두 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "스컬크러셔", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "원암 덤벨 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "롤링 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "딥스", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "파워 푸시업", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "오버헤드 케이블 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "오버헤드 덤벨 익스텐션", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "트라이셉스 딥스 머신", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "덤벨 케이블 크런치", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "팔굽혀펴기", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "덤벨 삼두 익스텐션(서있는 자세)", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "케이블 로프 푸시다운", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "숄더 푸시업", type = "삼두"))
        exerciseDataList.add(ExerciseData(name = "트라이셉스 익스텐션 머신", type = "삼두"))

        return binding.root
    }
}