package com.example.morethanyesterdayv2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morethanyesterdayv2.data.ExerciseData
import com.example.morethanyesterdayv2.databinding.FragmentBicepBinding
import com.example.morethanyesterdayv2.ui.adapter.ExerciseListAdapter

class BicepFragment: Fragment() {
    private val exerciseDataList = mutableListOf<ExerciseData>() // 데이터 리스트
    private lateinit var exerciseListAdapter: ExerciseListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentBicepBinding
    private lateinit var selectedDate: String

    companion object {
        private const val ARG_SELECTED_DATE = "selectedDate"
        fun newInstance(selectedDate: String): BicepFragment {
            val args = Bundle().apply {
                putString(ARG_SELECTED_DATE, selectedDate)
            }
            return BicepFragment().apply {
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
        binding = FragmentBicepBinding.inflate(inflater, container, false)

        recyclerView = binding.exerciseRv
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        exerciseListAdapter = ExerciseListAdapter(requireContext(), selectedDate)
        exerciseListAdapter.exerciseDataList = exerciseDataList

        recyclerView.adapter = exerciseListAdapter

        // exerciseDataList 초기화
        exerciseDataList.clear()
        // 운동 데이터 추가
        exerciseDataList.add(ExerciseData(name = "바벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "덤벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "해머컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "프리처컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "케이블컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "컨센트레이션컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "스컷컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "리버스컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "인클라인덤벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "프리쳐덤벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "프리쳐해머컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "인클라인바벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "해머프리처컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "버티컬바벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "오버헤드케이블익스텐션", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "오버헤드케이블컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "케이블로프컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "덤벨스탠딩컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "프리쳐바벨컬", type = "이두"))
        exerciseDataList.add(ExerciseData(name = "인클라인해머컬", type = "이두"))

        return binding.root
    }
}