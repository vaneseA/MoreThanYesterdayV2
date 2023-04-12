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
import com.example.morethanyesterdayv2.databinding.FragmentShoulderBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class ShoulderFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentShoulderBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): ShoulderFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return ShoulderFragment().apply {
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
        binding = FragmentShoulderBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "숄더프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "사이드 레터럴 레이즈", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "프론트 레터럴 레이즈", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "벤트오버 레터럴 레이즈", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "업라이트 로우", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "숄더 슈러그", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "페이스풀", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "스미스머신 숄더프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "밀리터리 프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "오버헤드 프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "리어 델트 풀다운", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "바벨 슈러그", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "파워 클린", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "푸쉬 프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "사이드 푸쉬 업", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "스핀드롭스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "버티컬 벤치 프레스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "시티드 딥스", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "카프 레이즈", type = "어깨"))
        exerciseDataList.add(ExerciseData(name = "저지드 프레스", type = "어깨"))

        return binding.root
    }
}