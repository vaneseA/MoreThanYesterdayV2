package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentBackBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class BackFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentBackBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): BackFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return BackFragment().apply {
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
        binding = FragmentBackBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "데드리프트", type = "등"))
        exerciseDataList.add(ExerciseData(name = "풀업", type = "등"))
        exerciseDataList.add(ExerciseData(name = "렛풀다운", type = "등"))
        exerciseDataList.add(ExerciseData(name = "케이블 시티드 로우", type = "등"))
        exerciseDataList.add(ExerciseData(name = "바벨로우", type = "등"))
        exerciseDataList.add(ExerciseData(name = "시티드로우", type = "등"))
        exerciseDataList.add(ExerciseData(name = "티바로우", type = "등"))
        exerciseDataList.add(ExerciseData(name = "바벨 슈라그", type = "등"))
        exerciseDataList.add(ExerciseData(name = "원암 덤벨로우", type = "등"))
        exerciseDataList.add(ExerciseData(name = "프론트 랫 풀다운", type = "등"))
        exerciseDataList.add(ExerciseData(name = "백 익스텐션", type = "등"))
        exerciseDataList.add(ExerciseData(name = "스쿼트", type = "등"))
        exerciseDataList.add(ExerciseData(name = "덤벨 런지", type = "등"))
        exerciseDataList.add(ExerciseData(name = "하프 데드리프트", type = "등"))
        exerciseDataList.add(ExerciseData(name = "시티드 카프 레이즈", type = "등"))
        exerciseDataList.add(ExerciseData(name = "랫 풀다운", type = "등"))
        exerciseDataList.add(ExerciseData(name = "스케토 런지", type = "등"))
        exerciseDataList.add(ExerciseData(name = "덤벨 바이셉 컬", type = "등"))
        exerciseDataList.add(ExerciseData(name = "웨이트드 크런치", type = "등"))
        exerciseDataList.add(ExerciseData(name = "레그프레스", type = "등"))

        return binding.root
    }
}