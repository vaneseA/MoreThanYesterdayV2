package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentAbsBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter


class AbsFragment : Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentAbsBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): AbsFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return AbsFragment().apply {
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
        binding = FragmentAbsBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "크런치", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "레그레이즈", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "플랭크", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "사이드 플랭크", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "힙 업", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "시즈 업", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "런지 크런치", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "크러치 크로스", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "하이 킥", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "로우 킥백", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "스쿼트", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "런지", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "사이드 런지", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "힙 브릿지", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "벽 등반", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "플랭크 니 터치", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "크러치", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "데드버그", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "복근 수축기", type = "복근"))
        exerciseDataList.add(ExerciseData(name = "트위스트 크런치", type = "복근"))

        return binding.root
    }
}